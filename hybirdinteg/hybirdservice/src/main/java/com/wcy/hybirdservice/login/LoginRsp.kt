package com.wcy.hybirdservice.login

import androidx.annotation.Keep


@Keep
data class RegisterRsp(
    val is_register: Int = FLAG_UN_REGISTERED
){
    companion object{
        const val FLAG_IS_REGISTERED = 1
        const val FLAG_UN_REGISTERED = 0
    }
}

typealias LoginRsp = CniaoUserInfo

data class CniaoUserInfo(
    val id: Int,//主键
    val course_permission: Boolean,
    val token: String?,
    var user: User?
){
    @Keep
    data class User(
        val id: Int,
        var logo_url:String?,
        val reg_time: String?,
        val username: String?
    )
}