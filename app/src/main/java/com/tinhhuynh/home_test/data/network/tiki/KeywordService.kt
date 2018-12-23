package com.tinhhuynh.home_test.data.network.tiki

import com.tinhhuynh.home_test.Constants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TikiService {

    @GET("keywords.json")
    fun fetchKeywords(): Observable<List<String>>

    companion object {
        fun create(): TikiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(Constants.KEYWORD_BASE_URL)
                    .build()

            return retrofit.create(TikiService::class.java)
        }
    }
}