# Glide 常见问题
## Glide加载回调中发起新的加载
> [debugging.html#其他常见问题](https://muyangmin.github.io/glide-docs-cn/doc/debugging.html#其他常见问题)
> 完整的错误堆栈信息
```java
java.lang.IllegalStateException: You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.
com.bumptech.glide.request.SingleRequest.l(TbsSdkJava)
com.bumptech.glide.request.SingleRequest.c(TbsSdkJava)
com.bumptech.glide.manager.RequestTracker.a(TbsSdkJava)
com.bumptech.glide.manager.RequestTracker.b(TbsSdkJava)
com.bumptech.glide.RequestManager.b(TbsSdkJava)
com.bumptech.glide.RequestManager.c(TbsSdkJava)
com.bumptech.glide.RequestManager.a(TbsSdkJava)
com.bumptech.glide.RequestBuilder.a(TbsSdkJava)
com.bumptech.glide.RequestBuilder.a(TbsSdkJava)
com.jiedai.loan.ui.activity.ADActivity.k(TbsSdkJava)
com.jiedai.loan.ui.activity.ADActivity$1.a(TbsSdkJava)
com.bumptech.glide.request.SingleRequest.a(TbsSdkJava)
com.bumptech.glide.request.SingleRequest.a(TbsSdkJava)
com.bumptech.glide.load.engine.EngineJob.f(TbsSdkJava)
com.bumptech.glide.load.engine.EngineJob$MainThreadCallback.handleMessage(TbsSdkJava)
android.os.Handler.dispatchMessage(Handler.java:98)
android.os.Looper.loop(Looper.java:135)
android.app.ActivityThread.main(ActivityThread.java:5418)
java.lang.reflect.Method.invoke(Native Method)
java.lang.reflect.Method.invoke(Method.java:372)
com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1037)
com.android.internal.os.ZygoteInit.main(ZygoteInit.java:832)
```


"You cant start or clear loads in RequestListener or Target callbacks"
如果你尝试在一个 Target 或 RequestListener 里的 onResourceReady 或 onLoadFailed 中开始一次新的加载，Glide 将会抛出一个异常。之所以抛出这个异常，是因为要处理和回收这种在通知过程中的 (notifying) 加载对我们来说是一个巨大的挑战。

好在这个问题很好解决。从 Glide 4.3.0 开始，你可以很轻松地使用 .error() 方法。这个方法接受一个任意的 RequestBuilder，它会且只会在主请求失败时开始一个新的请求：
```java
Glide.with(fragment)
  .load(url)
  .error(Glide.with(fragment)
     .load(fallbackUrl))
  .into(imageView);
```

## 
