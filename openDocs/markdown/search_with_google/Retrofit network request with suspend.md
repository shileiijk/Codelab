
# Network request with suspend and Call occur Error

> 
> ❎错误示例:
> ```kotlin
> @GET("api/user")
> suspend fun getUserData(): Call<UserData>
> ```
> suspend 和 Call 返回类型一起使用产生的问题会产生Unable to invoke no-args constructor for retrofit2.Call 这个报错
>

在 Retrofit 中，Call 是一个接口，用于表示一个 HTTP 请求的执行和响应。它的实现类通常是 OkHttpCall，它使用 OkHttp 库来执行网络请求。

当你在 Retrofit 接口方法中使用 suspend 关键字，并将返回类型声明为 Call 时，Retrofit 会尝试使用 Kotlin 协程来执行网络请求。
在这种情况下，Retrofit 会使用 suspendCancellableCoroutine 函数来创建一个挂起函数，并将其包装在一个 Call 对象中返回。

然而，如果你在接口方法中同时使用了 suspend 关键字和 Call 返回类型，Retrofit 会尝试直接实例化一个 Call 对象，而不是使用 suspendCancellableCoroutine 函数。
这就导致了报错 “Unable to invoke no-args constructor for retrofit2.Call”， 因为 Call 接口没有提供无参构造函数。

### ✅解决方案: 
+ suspend 接口返回类型使用 UserData 或者 Response<UserData>
```kotlin
@GET("api/user")
suspend fun getUserData(): Response<UserData>

@GET("api/user")
suspend fun getUserData(): UserData
```

+ 去掉suspend, 接口返回类型使用Call<UserData>
```kotlin
@GET("api/user")
fun getUserData(): Call<UserData>
```