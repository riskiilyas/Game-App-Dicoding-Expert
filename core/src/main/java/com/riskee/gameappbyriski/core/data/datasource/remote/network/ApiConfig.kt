package com.riskee.gameappbyriski.core.data.datasource.remote.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private var SERVICE: APIService? = null

    fun getApiService(context: Context): APIService {
        if (SERVICE == null) {
            val client = OkHttpClient.Builder()
                .addInterceptor(ChuckerInterceptor(context))
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.freetogame.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            SERVICE = retrofit.create(APIService::class.java)
        }

        return SERVICE!!
    }
}