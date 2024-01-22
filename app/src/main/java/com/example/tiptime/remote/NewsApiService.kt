package com.example.tiptime.remote

import com.example.tiptime.data.ArticlesList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")

    suspend fun getNews(
        @Query("country") country:String,
        @Query("category") category:String,
       /* @Query("q") query:String,*/
        @Query("pageSize") noItems: Int,


        @Query("apiKey") apiKey: String
    ): ArticlesList


}