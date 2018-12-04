# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#指定代码的压缩级别
-optimizationpasses 5
#包明不混合大小写
-dontusemixedcaseclassnames
#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
#优化 不优化输入的类文件
-dontoptimize
#预校验class_files
-dontpreverify
#混淆时是否记录日志
-verbose
# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#保护注解
-keepattributes *Annotation*
# 保持哪些类不被混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
#如果有引用v4包可以添加下面这行
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
#如果引用了v4或者v7包，可以忽略警告，因为用不到android.support
-dontwarn android.support.**

#忽略警告
-ignorewarning
##记录生成的日志数据,gradle build时在本项目根目录输出##
#apk 包内所有 class 的内部结构
#-dump proguard/class_files.txt
#未混淆的类和成员
#-printseeds proguard/seeds.txt
#列出从 apk 中删除的代码
#-printusage proguard/unused.txt
#混淆前后的映射
#-printmapping proguard/mapping.txt
########记录生成的日志数据,gradle build时 在本项目根目录输出-end######
#如果引用了v4或者v7包
-dontwarn android.support.**
####混淆保护自己项目的部分代码以及引用的第三方jar包library-end####

#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
native <methods>;
}
#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet);
}
#保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity {
public void *(android.view.View);
}
-keep public class * extends android.view.View {
public <init>(android.content.Context);
public <init>(android.content.Context, android.util.AttributeSet);
public <init>(android.content.Context, android.util.AttributeSet, int);
public void set*(...);
}
#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
public static final android.os.Parcelable$Creator *;
}
#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable
#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
static final long serialVersionUID;
private static final java.io.ObjectStreamField[] serialPersistentFields;
!static !transient <fields>;
!private <fields>;
!private <methods>;
private void writeObject(java.io.ObjectOutputStream);
private void readObject(java.io.ObjectInputStream);
java.lang.Object writeReplace();
java.lang.Object readResolve();
}
#保持枚举 enum 类不被混淆
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}
-keepclassmembers class * {
public void *ButtonClicked(android.view.View);
}
#不混淆资源类
-keepclassmembers class **.R$* {
public static <fields>;
}
#避免混淆泛型 如果混淆报错建议关掉
-keepattributes Signature
#移除Log类打印各个等级日志的代码,打正式包的时候可以做为禁log使用,这里可以作为禁止log打印的功能使用,另外的一种实现方案是通过BuildConfig.DEBUG的变量来控制
-assumenosideeffects class android.util.Log {
public static *** v(...);
public static *** i(...);
public static *** d(...);
public static *** w(...);
public static *** e(...);
}
#gson
#如果用用到Gson解析包的,直接添加下面这几行就能成功混淆,不然会报错。
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }
#mob
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class m.framework.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn cn.sharesdk.**
-dontwarn **.R$*
#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
@butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
@butterknife.* <methods>;
}
# 如果使用了Gson之类的工具要使被它解析的JavaBean类即实体类不被混淆。
-keep class com.lecloud.base.** { *; }
-keep class com.lecloud.imlib.** { *; }
-keep class com.lecloud.leblockmodelmanager.** { *; }

# --------------- 友盟统计避免混淆 -------------------------
-dontwarn Android.support.v4.**
-dontwarn org.apache.commons.net.**
-dontwarn com.tencent.**
-keepclasseswithmembernames class * {
native <methods>;
}
-keepclasseswithmembernames class * {
public <init>(Android.content.Context, Android.util.AttributeSet);
}
-keepclasseswithmembernames class * {
public <init>(Android.content.Context, Android.util.AttributeSet, int);
}
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}
-keep class * implements Android.os.Parcelable {
public static final Android.os.Parcelable$Creator *;
}
-keepclasseswithmembers class * {
public <init>(Android.content.Context);
}
-dontshrink
-dontoptimize
-dontwarn com.google.Android.maps.**
-dontwarn Android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**
-keep public class com.umeng.socialize.* {*;}
-keep public class javax.**
-keep public class Android.webkit.**
-keep class com.facebook.**
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.tencent.mm.sdk.openapi.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.openapi.** implements com.tencent.mm.sdk.openapi.WXMediaMessage$IMediaObject {*;}
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
-keep public class [your_pkg].R$*{
public static final int *;
}
#极光推送
-dontoptimize
-dontpreverify
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
#retroift
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
#ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
@butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
@butterknife.* <methods>;
}
#fastjson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }
#webView处理,项目中没有使用到webView忽略即可
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
public void *(android.webkit.webView, jav.lang.String);
}
#融云
-keepattributes Exceptions,InnerClasses
-keepattributes Signature
# RongCloud SDK
-keep class io.rong.** {*;}
-keep class * implements io.rong.imlib.model.MessageContent {*;}
-dontwarn io.rong.push.**
-dontnote com.xiaomi.**
-dontnote com.google.android.gms.gcm.**
-dontnote io.rong.**
# VoIP
-keep class io.agora.rtc.** {*;}
# Location
-keep class com.amap.api.**{*;}
-keep class com.amap.api.services.**{*;}
# 红包
-keep class com.google.gson.** { *; }
-keep class com.uuhelper.Application.** {*;}
-keep class net.sourceforge.zbar.** { *; }
-keep class com.google.android.gms.** { *; }
-keep class com.alipay.** {*;}
-keep class com.jrmf360.rylib.** {*;}
-ignorewarnings
#播放器框架
-keep class tv.danmaku.ijk.media.player.** {*;}