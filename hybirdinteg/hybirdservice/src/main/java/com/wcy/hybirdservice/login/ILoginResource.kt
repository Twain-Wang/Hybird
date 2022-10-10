package com.wcy.hybirdservice.login

interface ILoginResource {
    /**
     * 校验手机号
     */
    suspend fun checkRegister(mobi: String)

    /**
     * 手机合法调登录
     */
    suspend fun requestLogin(body: LoginReqBody)
}