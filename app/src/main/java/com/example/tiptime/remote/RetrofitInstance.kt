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
        private const val CERTIFICATE_PIN = "sha256/A+L5NEZLzX9+Tzc2Y5TMTKjdRlaasLKndpTU0hrW6jI="
        private const val CERTIFICATE_WITH= "sha256/E4:30:6F:B3:90:C3:DF:7A:74:9A:B7:A8:2D:1F:78:6F:30:25:55:45:3D:7D:D9:04:67:C1:FE:90:0F:0C:80:55"

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
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}
