# BlackObfuscator-GUI
This project is an graphical utility based on [BlackObfuscator](https://github.com/CodingGay/BlackObfuscator). More information about this project are in [BlackObfuscator](https://github.com/CodingGay/BlackObfuscator).

#### Prerequisite
JDK11 or later

#### Usage
1. Download zip file in [releases](https://github.com/CodingGay/BlackObfuscator-GUI/releases).
2. Unzip and double clike clickme.bat(Windows) or clickme.sh(MacOS).

#### Parameter
Parameter | Description 
---|---
Input | APK or DexFile Path
Output | APK or DexFile output path 
Depth | Obfuscation depth (The higher that number is, the more complex it is to obfuscate the code)
Rules | The packages which need to be obfuscated 

#### Obfuscation Rules
##### Provide the classes which need to be obfuscated
```x
#it is annotation
#cn.kaicity

#package
cn.kaicity.gk.cdk.BuildConfig

#class
cn.kaicity

#blackList
!cn.kaicity.gk.cdk

#It will not obfuscate the package/class that in blackList
```

#### Preview

![](images/image1.png)

![](images/image2.png)

![](images/image3.png)
