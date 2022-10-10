package com.wcy.hybirdhttp

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class LocalCookieJar : CookieJar {
    private val cache = mutableListOf<Cookie>()


    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val invalidCookies: MutableList<Cookie> = ArrayList()
        val validCookies: MutableList<Cookie> = ArrayList()

        for (cookie: Cookie in cache){
            if (cookie.expiresAt < System.currentTimeMillis()){
                invalidCookies.add(cookie)
            }else if (cookie.matches(url)){
                validCookies.add(cookie)
            }
        }
        cache.removeAll(invalidCookies)
        return validCookies
    }


    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cache.addAll(cookies)
    }
}