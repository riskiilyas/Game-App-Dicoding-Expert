package com.riskee.gameappbyriski.core.data.datasource.remote.network

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    private var SERVICE: APIService? = null

    fun getApiService(): APIService {
        if (SERVICE == null) {
            val hostname = "www.freetogame.com"
            val certificatePinner = CertificatePinner.Builder()
                .add(hostname, "sha256/ygejhvq1xCIr0vJmdjhAnaE+hYzikddoNWR1eZrLUoI=")
                .add(hostname, "sha256/8kGWrpQHhmc0jwLo43RYo6bmqtHgsNxhARjM5yFCe/w=")
                .add(hostname, "sha256/gI1os/q0iEpflxrOfRBVDXqVoWN3Tz7Dav/7IT++THQ=")
                .build()
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
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