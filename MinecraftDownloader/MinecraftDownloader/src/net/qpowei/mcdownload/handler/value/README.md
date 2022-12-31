##下载流程

1. 下载 **version_manifest(_v2).json** 下文简称 *manifest* (对应GSON类 **VersionList**)
2. 在 *manifest* 中得到需要下载的版本下载对应的*版本资源文件*(比如 1.12.2.json) (*版本资源文件*对应GSON类**VersionIndex**)
3. 在 *版本资源文件* 中得到assets index文件URL.并下载依赖库
4. 在assets index中获得其它文件URL
