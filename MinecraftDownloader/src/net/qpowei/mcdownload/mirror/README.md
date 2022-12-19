这里可能会有一点混乱

`DefaultInjector`/`DefaultURLPath` 使用provider是为了方便改变源，这样只需要在DefaultProviders中调用setXXX即可

`DefaultInjector`: 专门对url进行处理，通过provider进行换源
`DefaultURLPath`: 提供所需资源文件的URL和对应的文件保存路径
`DefaultMirrors`: 包含所有默认提供的源
