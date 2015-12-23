# AndroidDevelopment
JNI and JNA on Android Studio

###教程一：在Android Studio里使用JNI  
####环境
- Android Studio 1.5.1
- 已经下载内置ndk

####具体操作
- 新建一个工程，命名为`JNI on AS`，点击next，选择最低的开发版本，点击next，选择一个空的Activity, 点击next，设置Activity的名字为默认的MainActivity，点击Finish
- 选择View->Tool Windows->Project，有时候可能因为已经出现了得再点击一下，就是看到左边栏即可。选择Project（不选择Android）
- 依次点击app，src，main逐层展开，再main目录下右键->New->Folder->JNI Folder->Finish
- 点击java目录，在MainActivity所在的package里面新增加一个java类`JNIKit`，并输入以下代码以构建出Java端想要用的函数形式，下一步我们会构建一个名字为JNIDemo的库函数

	```java
	public class JNIKit {
		//计算平方
	    public static native int square(int num);
	    //获取欢迎信息
	    public static String welcome();
	    static {
	        System.loadLibrary("JNIDemo");
	    }
	}
	```
- 打开Android Studio的Terminal，进入java目录，`${PROJECT_ROOT}/app/src/main/java`，输入命令行参数生成JNI头文件
```java
javah -jni sadcup.android.jnionas.JNIKit
```
其中每个字端都要与之后的目录结构对应上，就是一直到JNIKit.java的路径，这样在java目录下面就生成了头文件，把这个头文件移动到jni目录下面，并创建一个同名字的.cpp文件，在cpp文件里实现两个函数

```cpp
JNIEXPORT jint JNICALL Java_sadcup_android_jnionas_JNIKit_square
        (JNIEnv * env, jclass obj, jint num) {
    return num * num;
}

JNIEXPORT jstring JNICALL Java_sadcup_android_jnionas_JNIKit_welcome
        (JNIEnv * env, jclass obj) {
    return (env)->NewStringUTF("I am from JNI");
}
```
	
- Build->Make Project，会提示错误
> Error:Execution failed for task ':app:compileDebugNdk'.
> Error: NDK integration is deprecated in the current plugin.  Consider trying the new experimental plugin.  For details, see http://tools.android.com/tech-docs/new-build-system/gradle-experimental.  Set "android.useDeprecatedNdk=true" in gradle.properties to continue using the current NDK integration.

这是因为原来的ndk编辑方式会被更新替代掉，但是[目前处于试验版本](http://ph0b.com/new-android-studio-ndk-support/)，所以要进行一些修改，也就是按照错误的提示进行修改。
在gradle.properties里设置`android.useDeprecatedNdk=true`
- 在app的build.gradle里android->defaultConfig下进行如下代码设置NDK编译出的库函数的名称
、

```
ndk {
	moduleName "JNIDemo"
}
```
- 在MainActivity里面调用JNI函数

```java
String str = JNIKit.welcome();
str += " and 2 * 2 = " + JNIKit.square(2);
((TextView)findViewById(R.id.text)).setText(str);
```
- 最终显示结果为

```
I am from JNI and 2 * 2 = 4
```

###教程二 在Android Studio里使用JNA
####环境
- Android Studio 1.5.1
- 已经下载内置ndk

####具体操作

- 准备JNA开发环境
	- 这个部分是最重要的，也是与JNI的例子主要不同之处之一
	- 十分感谢[这篇文章]((https://github.com/pakoito/jna))，之前查找了好多博客，也做了很多尝试，终于这个里面的jna.jar是有效的。这个虽然不是官方的jna开源位置，但是是为android单独配置的
	- 下载或者clone源代码
	- 运行命令`ant -Dos.prefix=android-arm dist`
	- 将dist下的`jna.rar`拷贝到工程的`app/src/main/libs`文件夹下，没有的话新建一个libs，右键->Add as library
	- 在`app/src/main/`建立`jniLibs/armeabi`,`jniLibs/x86`
	- 把dist下的android-arm.jar解压缩并拷贝libjnidispatch.so拷贝到`app/src/main/jniLibs/armeabi`里面
	- 把dist虾的android-x86.jar解压缩病拷贝libjnidispatch.so拷贝到`app/src/main/jniLibs/x86`里面
	- 如果是armeabi-v7a的话，就再建立一个jniLibs/armeabi-v7a文件夹，并共用jniLibs/armeabi的so文件即可
	- 如果运行中发现错误，那可能是一些环境变量没有设置
	
		```bash
		export ANDROID_NDK=/Users/xxxx/Library/Android/sdk/ndk-bundle
		export ANDROID_SDK=/Users/xxxx/Library/Android/sdk
		```
- 新建工程
	- 起个名字叫做JNA on AS
- 配置NDK开发环境
	- 进入1.Project的侧边栏，选择Project显示
	- 在app/src/main下面右键，New->Folder->JNI Folder
	- 在gradle.properties里面加入`android.useDeprecatedNdk = true`
- 编写C/C++库函数
	- app中的build.gradle里面的android->defaultConfig里给库函数起一个名字
	
		```
		ndk {
	   		moduleName "JNADemo"
		}
		```
	- 在jni文件夹里右键->New->C++ Class，起个名字叫JNIDemoInterface，作为之后与Java对接的接口
	- 在jni文件夹里右键->New->C++ Class，起个名字叫JNIDemo，作为真正的数据处理类
	- 假定需要完成的功能为java端传递一个字符串，和想要的开始和结尾索引，c++端完成对这段数字的获取并传递回给java端
	- 具体代码如例子所示
