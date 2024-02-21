
# Show dialog with application context

>
> 一般的 Dialog 对象初始化都需要依赖于 Activity 
> 
> 虽说 AlertDialog.Builder(Context context) 的参数是 Context，但是如果传入的是非 Activity 的 Context，比如说 Application，就会报如下所示的错误
> ```java
> 12:05:26.870 25517-25517 AndroidRuntime D  Shutting down VM
> 12:05:26.872 25517-25517 AndroidRuntime E  FATAL EXCEPTION: main
>         Process: com.tcl.tv.plus, PID: 25517
>         android.view.WindowManager$BadTokenException: Unable to add window -- token null is not valid; is your activity running?
>         at android.view.ViewRootImpl.setView(ViewRootImpl.java:1068)
>         at android.view.WindowManagerGlobal.addView(WindowManagerGlobal.java:409)
>         at android.view.WindowManagerImpl.addView(WindowManagerImpl.java:109)
>         at android.app.Dialog.show(Dialog.java:340)
>         ...
>         at android.os.Handler.handleCallback(Handler.java:938)
>         at android.os.Handler.dispatchMessage(Handler.java:99)
>         at android.os.Looper.loop(Looper.java:223)
>         at android.app.ActivityThread.main(ActivityThread.java:7656)
>         at java.lang.reflect.Method.invoke(Native Method)
>         at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:592)
>         at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:947)
> 
> ```
>

## 全局 Dialog
但是如果说有些情况下获取不到 Activity 对象，但是又想弹出 Dialog 呢？这时候用全局的 Dialog 是可以实现的。
只需要设置 dialog 为 WindowManager.LayoutParams.TYPE_SYSTEM_ALERT 类型
然后添加 android.permission.SYSTEM_ALERT_WINDOW 权限

```java
Dialog dialog = new AlertDialog.Builder(activity.getApplicationContext())
        .setTitle("XXXX")
        .setMessage("我是对话框内容啦")
        .create();

// 增加这句代码
dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
dialog.show();

// 然后在manifest中添加权限
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
```
