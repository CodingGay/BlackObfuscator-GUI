# BlackObfuscator-GUI

> BlackObfuscator GUI 工具

需要打包成jar包，然后将BlackObfuscator下载到本地，然后重命名成dex-tools
> 可直接在release界面下载解压运行
> 虽然目前只提供了Windows的Release包，但是我觉得能够在所有平台上面运行，毕竟是jar

1.前往BlackObfuscator Release界面下载最新的工具
 > https://github.com/CodingGay/BlackObfuscator/releases

2.解压，并重命名为dex-tools

3.使用Gradle-compose desktop - packageUberJarForCurrentOS 任务生成本地jar包

4.移动dex-tools到jar同一目录

>dex-tools
> > lib
>
> > bin
>
> BlackObfuscator.jar
