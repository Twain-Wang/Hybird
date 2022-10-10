package com.wcy.hybirdinteg.modules

import android.content.Intent
import com.alibaba.fastjson.JSONObject
import com.wcy.hybirdcommon.utils.BaseUrl
import com.wcy.hybirdhttp.KtRetrofit
import com.wcy.hybirdinteg.MainApplication
import com.wcy.hybirdservice.mine.MineService
import com.wcy.hybirdservice.network.BaseCniaoRsp
import com.wcy.jnative.TestActivity
import io.dcloud.feature.uniapp.annotation.UniJSMethod
import io.dcloud.feature.uniapp.bridge.UniJSCallback
import io.dcloud.feature.uniapp.common.UniModule
import io.flutter.embedding.android.FlutterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MineModule : UniModule(){
    @UniJSMethod(uiThread = true)
    fun getMineList(option: JSONObject,callback: UniJSCallback){
        val userId: String = option.getString("userId")
        KtRetrofit.initConfig(BaseUrl.baseUrl)
            .getService(MineService::class.java)
            .getMineList(userId)
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

    @UniJSMethod(uiThread = true)
    fun gotoModule(option: JSONObject,callback: UniJSCallback){
        try {
            val itemName: String = option.getString("itemName")
            val itemId:Int = option.getInteger("itemId")
            when(itemId){
                1 ->{
                    //跳转到原生页面
                    println("跳转到原生页面")
                    val intent: Intent = Intent(MainApplication.getCurrActivity(),TestActivity::class.java)
                    MainApplication.getCurrActivity().startActivityForResult(intent,1000)
                }
                3 ->{
                    //跳转到flutter页面
                    println("跳转到flutter页面")
                    MainApplication.getCurrActivity().startActivity(
                        FlutterActivity
                            .withCachedEngine("flutter_native")
                            .build(MainApplication.getCurrActivity())
                    )

                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }

    }
}