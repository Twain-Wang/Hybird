package com.wcy.hybirdservice.network

import androidx.annotation.Keep
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.wcy.hybirdhttp.DataResult
import com.wcy.hybirdhttp.succeeded
import org.json.JSONObject
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@Keep
class BaseCniaoRsp(
    val code: Int,
    val data: Any?,
    val message: String?
){
    companion object {
        const val SERVER_CODE_FAIELD = 0
        const val SERVER_CODE_SUCCESS = 1
    }
}
/**
 * 这里表示网络请求成功，并得到业务服务器的响应，至于业务成功失败另一说
 * 将BaseCniaoRsp的对象转化为需要的对象类型，也就是body.string转为entity
 * @return 返回需要的类型对象 可能为null 如果json解析失败的话
 */
inline fun <reified T> BaseCniaoRsp.toEntity(): T? {
    if (data == null){
        LogUtils.e("Server Response Json Ok, But data=null, $code,$message")
        return null
    }
    if (T::class.java.isAssignableFrom(String::class.java)){
        return null
    }
    return kotlin.runCatching {
        GsonUtils.fromJson(this.data.toString(),T::class.java)
    }.onFailure { e ->
        e.printStackTrace()
    }.getOrNull()
}

/**
 * 扩展用于处理网络返回数据结果，网络接口请求成功，但是业务成功与否，另一说
 */
@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onSuccess(action: R.() -> Unit
) : DataResult<R> {
    contract {
        callsInPlace(action,InvocationKind.AT_MOST_ONCE)
    }
    if (succeeded){
        action.invoke((this as DataResult.Success<R>).data)
    }
    return this
}



/**
 * 这是网络请求出现错误的时候的回调
 */
@OptIn(ExperimentalContracts::class)
inline  fun <R> DataResult<R>.onFailure(
    action: (exception: Throwable) -> Unit
): DataResult<R> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is DataResult.Error){
        action.invoke(exception)
    }
    return this
}

/**
 * 接口成功，但是业务返回code不是1的情况
 */
@OptIn(ExperimentalContracts::class)
inline fun BaseCniaoRsp.onBizError(crossinline  block: (code: Int,message: String) -> Unit) :BaseCniaoRsp{
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    if (code != BaseCniaoRsp.SERVER_CODE_SUCCESS){
        block.invoke(code,message ?: "Error Message Null")
    }
    return this
}

/**
 * 接口成功，但是业务返回code是1的情况
 */
@OptIn(ExperimentalContracts::class)
inline fun <reified T> BaseCniaoRsp.onBizOk(crossinline  action: (code: Int, data: T?, message: String?) -> Unit): BaseCniaoRsp {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    if (code == BaseCniaoRsp.SERVER_CODE_SUCCESS){
        action.invoke(code, this.toEntity<T>(),message)
    }
    return  this
}