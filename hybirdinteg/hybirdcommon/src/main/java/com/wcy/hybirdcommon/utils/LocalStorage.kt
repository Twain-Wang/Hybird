package com.wcy.hybirdcommon.utils

import android.content.Context
import android.content.SharedPreferences

class LocalStorage {
    companion object{
        @JvmStatic
        fun saveSPInfo(context: Context,keyInfo: String,valueInfo: String){
            val sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE)
            val edit: SharedPreferences.Editor = sharedPreferences.edit()
            edit.putString(keyInfo,valueInfo)
            edit.apply()
        }
        @JvmStatic
        fun getSPInfo(context: Context,keyInfo: String): String{
            val sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE)
            val valueInfo: String? = sharedPreferences.getString(keyInfo,"")
            return valueInfo!!
        }
    }
}