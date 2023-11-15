package com.example.tiptime.remote

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitInstance {

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"

        private const val HOSTNAME = "newsapi.org"
        private const val CERTIFICATE_PIN = "sha256/E4306FB390C3DF7A749AB7A82D1F786F302555453D7DD90467C1FE900F0C8055"

        private val certificatePinner = CertificatePinner.Builder()
            .add(HOSTNAME, CERTIFICATE_PIN)
            .build()

        private val okHttpClient = OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .build()
    }





    fun <Api> buildApi(api: Class<Api>): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)

            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}
