package com.wcy.hybirdservice.mine

import com.wcy.hybirdservice.network.BaseCniaoRsp
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface MineService {
    @POST("mine_list")
    fun getMineList(@Query("userId")userId: String): Call<BaseCniaoRsp>
}