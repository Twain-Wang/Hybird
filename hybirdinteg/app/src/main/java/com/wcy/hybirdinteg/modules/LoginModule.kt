package com.wcy.hybirdinteg.modules

import com.alibaba.fastjson.JSONObject
import com.blankj.utilcode.util.GsonUtils
import com.wcy.hybirdcommon.utils.BaseUrl
import com.wcy.hybirdcommon.utils.LocalStorage
import com.wcy.hybirdhttp.KtRetrofit
import com.wcy.hybirdinteg.MainApplication
import com.wcy.hybirdservice.login.LoginReqBody
import com.wcy.hybirdservice.login.LoginService
import com.wcy.hybirdservice.network.*
import io.dcloud.feature.uniapp.annotation.UniJSMethod
import io.dcloud.feature.uniapp.bridge.UniJSCallback
import io.dcloud.feature.uniapp.common.UniModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginModule() : UniModule() {
    @UniJSMethod(uiThread = true)
    fun checkRegister(option: JSONObject, callback: UniJSCallback){
        val phone: String = option.getString("phone")
        try {
            KtRetrofit.initConfig("https://www.fastmock.site/mock/01947bc519c02d4924a5b7c3e8bb384c/wcy/")
                .getService(LoginService::class.java)
                .isRegister(phone)
                .enqueue(object : Callback<BaseCniaoRsp>{
                    override fun onResponse(
                        call: Call<BaseCniaoRsp>,
                        response: Response<BaseCniaoRsp>
                    ) {
                        callback.invoke(GsonUtils.toJson(response.body()))
                    }

                    override fun onFailure(call: Call<BaseCniaoRsp>, t: Throwable) {
                        callback.invoke(t.message.toString())
                    }
                })

        }catch (ex: Exception){
            ex.printStackTrace()
        }
    }

    @UniJSMethod(uiThread = true)
    fun loginAction(option: JSONObject,callback: UniJSCallback){
        KtRetrofit.initConfig(BaseUrl.baseUrl)
            .getService(LoginService::class.java)
            .login(LoginReqBody(option.getString("phone"),option.getString("password")))
            .enqueue(object : Callback<BaseCniaoRsp>{
                override fun onResponse(
                    call: Call<BaseCniaoRsp>,
                    response: Response<BaseCniaoRsp>
                ) {
                    val map = mapOf<String,String>("login" to "yes");
                    callback.invoke(map)
                    val baseCniaoRsp: BaseCniaoRsp? = response.body();
                    val info: String = JSONObject.toJSONString(baseCniaoRsp)
                    LocalStorage.saveSPInfo(MainApplication.getCurrActivity(),"userinfo",info)
                }

                override fun onFailure(call: Call<BaseCniaoRsp>, t: Throwable) {
                    val map = mapOf<String,String>("login" to "no");
                    callback.invoke(map.toString())
                }
            })
    }

    @UniJSMethod(uiThread = true)
    fun checkLogin(callback: UniJSCallback){
        try{
            val userInfo: String = LocalStorage.getSPInfo(MainApplication.getCurrActivity(),"userinfo")
            callback.invoke(userInfo)
        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    @UniJSMethod(uiThread = true)
    fun testMethod(option: JSONObject,callback: UniJSCallback){
        NativeModule.sendMessage();
    }
}