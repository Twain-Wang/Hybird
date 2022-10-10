package com.wcy.hybirdinteg.modules

import com.alibaba.fastjson.JSONObject
import com.wcy.hybirdcommon.utils.BaseUrl
import com.wcy.hybirdhttp.KtRetrofit
import com.wcy.hybirdservice.home.HomeService
import com.wcy.hybirdservice.network.BaseCniaoRsp
import io.dcloud.feature.uniapp.annotation.UniJSMethod
import io.dcloud.feature.uniapp.bridge.UniJSCallback
import io.dcloud.feature.uniapp.common.UniModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeModule : UniModule(){
    @UniJSMethod(uiThread = true)
    fun getHomeBanner(option: JSONObject,callback: UniJSCallback){
        val userId: String = option.getString("userId")
        KtRetrofit.initConfig(BaseUrl.baseUrl)
            .getService(HomeService::class.java)
            .getHomeBanner(userId)
            .enqueue(object: Callback<BaseCniaoRsp>{
                override fun onResponse(
                    call: Call<BaseCniaoRsp>,
                    response: Response<BaseCniaoRsp>
                ) {
                    val baseCniaoRsp: BaseCniaoRsp? = response.body();
                    val info: String = JSONObject.toJSONString(baseCniaoRsp)
                    callback.invoke(info)
                }

                override fun onFailure(call: Call<BaseCniaoRsp>, t: Throwable) {

                }
            })
    }
}