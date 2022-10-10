package com.wcy.hybirdservice.login

import com.wcy.hybirdservice.network.BaseCniaoRsp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {
    @GET("accounts/phone/is/register")
    fun isRegister(@Query("mobi") mobi: String): Call<BaseCniaoRsp>

    @POST("login")
    fun login(@Body reqBody: LoginReqBody): Call<BaseCniaoRsp>
}