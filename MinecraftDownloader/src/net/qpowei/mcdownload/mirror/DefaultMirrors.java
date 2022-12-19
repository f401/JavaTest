package net.qpowei.mcdownload.mirror;

public class DefaultMirrors
{
	public static DefaultMirror MOJANG = 
	   new DefaultMirror(
	   "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json",
	   "https://resources.download.minecraft.net/",
	   "^(https://)libraries\\.minecraft\\.net/",
	   "^(https://)((piston\\-data\\.mojang\\.com/)|(launcher\\.mojang\\.com/))",
	   "^(https://)((piston\\-meta\\.mojang\\.com/)|(launchermeta\\.mojang\\.com/))",
	   "^(https://)files\\.minecraftforge\\.net/maven/", 
	   "Default"
	);
	public static final DefaultMirror BMCLAPI = new DefaultMirror(
	    "https://bmclapi2.bangbang93.com/mc/game/version_manifest_v2.json",
		"https://bmclapi2.bangbang93.com/assets/", 
		"https://bmclapi2.bangbang93.com/maven/",
		"https://bmclapi2.bangbang93.com/", 
		"https://bmclapi2.bangbang93.com/",
		"https://bmclapi2.bangbang93.com/maven/", 
		"BMCLAPI"
	);
}
