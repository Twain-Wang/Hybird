package com.wcy.hybirdservice.home

import com.wcy.hybirdservice.network.BaseCniaoRsp
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeService {
    @POST("home/banners")
    fun getHomeBanner(@Query("userId")userId: String):Call<BaseCniaoRsp>

}