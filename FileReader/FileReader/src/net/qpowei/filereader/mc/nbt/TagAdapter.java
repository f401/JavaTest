package net.qpowei.filereader.mc.nbt;

import net.qpowei.filereader.mc.nbt.tags.*;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class TagAdapter extends TypeAdapter<Tag<?>> {

	private static interface NumberResolverCallBack<T, P> {
		T intValue(P param, int value);

		T longValue(P param, long value);

		T floatValue(P param, String value);
	}

	public static GsonBuilder configGsonBuilder(GsonBuilder src) {
		return src.registerTypeHierarchyAdapter(Tag.class, new TagAdapter());
	}

	private TagAdapter() {
	}

	@Override
	public Tag<?> read(JsonReader reader) throws IOException {
		if (reader.hasNext()) {
			String key = null;
			{
				if (JsonToken.NAME == reader.peek()) {
					key = reader.nextName();
				}
			}
			switch (reader.peek()) {
				case NUMBER:
					return this.<Tag<?>, String>resolveNumber(
							new NumberResolverCallBack<Tag<?>, String>() {

								@Override
								public Tag<?> intValue(String param, int value) {
									return new IntTag(param, value);
								}

								@Override
								public Tag<?> longValue(String param, long value) {
									return new LongTag(param, value);
								}

								@Override
								public Tag<?> floatValue(String param, String value) {
									return new DoubleTag(param,
											Double.parseDouble(value));
								}
							}, reader, key);
				case STRING:
					return new StringTag(key, reader.nextString());
				case BEGIN_OBJECT:
					ArrayList<Tag<?>> compoundTags = new ArrayList<>();
					reader.beginObject();
					while (reader.hasNext()) {
						compoundTags.add(read(reader));
					}
					reader.endObject();
					return new CompoundTag(key, compoundTags);
				case BEGIN_ARRAY:// ListTag IntArrayTag LongArrayTag
					reader.beginArray();
					Tag<?> result = null;
					switch (reader.peek()) {
						case NUMBER:
							BaseArrayTag<?> array = this
									.<BaseArrayTag<?>, String>resolveNumber(
											new NumberResolverCallBack<BaseArrayTag<?>, String>() {

												@Override
												public BaseArrayTag<?> intValue(
														String param,
														int value) {
													ArrayList<Integer> list = new ArrayList<>();
													list.add(value);
													return new IntArrayTag(
															param,
															list);
												}

												@Override
												public BaseArrayTag<?> longValue(
														String param,
														long value) {
													ArrayList<Long> list = new ArrayList<>();
													list.add(value);
													return new LongArrayTag(
															param,
															list);
												}

												@Override
												public BaseArrayTag<?> floatValue(
														String param,
														String value) {
													ArrayList<Tag<?>> list = new ArrayList<>();
													list.add(new DoubleTag(
															null,
															Double.parseDouble(
																	value)));
													return new ListTag(
															param,
															list,
															TagTypes.Double);
												}
											}, reader, key);
							while (reader.hasNext()) {
								this.<Void, BaseArrayTag<?>>resolveNumber(
										new NumberResolverCallBack<Void, BaseArrayTag<?>>() {

											@Override
											public Void intValue(
													BaseArrayTag<?> param,
													int value) {
												IntArrayTag array = (IntArrayTag) param;
												array.add(value);
												return null;
											}

											@Override
											public Void longValue(
													BaseArrayTag<?> param,
													long value) {
												LongArrayTag array = (LongArrayTag) param;
												array.add(value);
												return null;
											}

											@Override
											public Void floatValue(
													BaseArrayTag<?> param,
													String value) {
												ListTag array = (ListTag) param;
												array.add(new DoubleTag(
														null,
														Double.valueOf(value)));
												return null;
											}
										}, reader, array);
							}
							result = array;
							break;
						case BEGIN_OBJECT:
							ArrayList<Tag<?>> list = new ArrayList<>();
							while (reader.hasNext()) {
								list.add(read(reader));
							}
							result = new ListTag(key, list);
							break;
						case STRING:
							ArrayList<Tag<?>> list2 = new ArrayList<>();
							while (reader.hasNext()) {
								list2.add(new StringTag(null, reader.nextString()));
							}
							result = new ListTag(key, list2, TagTypes.String);
							break;
						case END_ARRAY:
						case END_OBJECT:
							result = new IntArrayTag(key, null);
							break;
						default:
							throw new RuntimeException(reader.peek().toString());
					}
					reader.endArray();
					return result;
				case END_ARRAY:
				case END_OBJECT:
					break;
				default:
					throw new RuntimeException(reader.peek().toString());
			}
		}
		return null;
	}

	@Override
	public void write(JsonWriter arg0, Tag<?> arg1) throws IOException {
		arg1.write(arg0);
	}

	private <T, P> T resolveNumber(NumberResolverCallBack<T, P> callBack, JsonReader reader, P param)
			throws IOException {
		String number = reader.nextString();
		if (number == null)
			return null;
		if (number.contains(".")) {
			return callBack.floatValue(param, number);
		}
		long longValue = Long.parseLong(number);
		if (longValue <= Integer.MAX_VALUE && longValue >= Integer.MIN_VALUE) {
			return callBack.intValue(param, Integer.parseInt(number));
		}
		return callBack.longValue(param, longValue);
	}

}
