package com.wcy.hybirdservice.login

import androidx.annotation.Keep

@Keep
data class LoginReqBody (
    val mobi: String,
    val password: String
 )