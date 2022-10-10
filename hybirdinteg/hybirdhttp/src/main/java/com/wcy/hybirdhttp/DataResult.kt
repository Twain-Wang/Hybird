package com.wcy.hybirdhttp

import java.lang.Exception

sealed class DataResult<out T> {
    //成功的状态
    data class Success<out T>(val data: T) : DataResult<T>()

    //错误,失败的状态
    data class Error(val exception: Exception) : DataResult<Nothing>()

    //加载数据中
    object Loading : DataResult<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }

    }
}

val DataResult<*>.succeeded: Boolean
    get() = this is DataResult.Success && data != null