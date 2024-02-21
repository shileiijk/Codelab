package com.shilei.ijk.wandroid.api

import com.shilei.ijk.okretro.anno.AuthToken
import com.shilei.ijk.okretro.BaseResponse
import com.shilei.ijk.okretro.anno.AuthTokenHead
import com.shilei.ijk.wandroid.data.model.LoginResponse
import com.shilei.ijk.wandroid.data.model.RegisterResponse
import com.shilei.ijk.wandroid.data.bean.ArticleList
import com.shilei.ijk.wandroid.data.bean.Banner
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface WandroidService {
    @GET("/article/list/{page}/json")
    fun listHomeArticles(@Path("page") page: Int): Call<BaseResponse<ArticleList>>

    @GET("/banner/json")
    fun requestBanner(): Call<BaseResponse<List<Banner>>>

    /**
     * 登录API
     * username=Derry-vip&password=123456
     */
    @POST("/user/login")
    @FormUrlEncoded
    fun loginAction(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<BaseResponse<LoginResponse>> // 返回值

    /**
     * 注册的API
     */
    @Headers("${AuthTokenHead.AUTH_TOKEN}${AuthTokenHead.COMMA}${AuthTokenHead.FALSE}")
    @AuthToken(needAuthToken = false)
    @POST("/user/register")
    @FormUrlEncoded
    fun registerAction(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Observable<BaseResponse<RegisterResponse>> // 返回值
}