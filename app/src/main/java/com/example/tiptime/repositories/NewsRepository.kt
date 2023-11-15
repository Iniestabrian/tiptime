package com.example.tiptime.repositories

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tiptime.data.ArticleX
import com.example.tiptime.data.ArticlesList
import com.example.tiptime.remote.NewsApiService
import com.example.tiptime.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsRepository  {

    private val retrofitInstance = RetrofitInstance()

    private val apiKey = "c9242f1de0b14fddb655956487b707f3"

    suspend fun getNews(): List<ArticleX> {
        return withContext(Dispatchers.IO) {
            try {
                val apiService = retrofitInstance.buildApi(NewsApiService::class.java)
                val response = apiService.getNews("us", "technology", 20, apiKey)
                if (response.isSuccessful) {
                    response.body()?.articles ?: emptyList()
                } else {
                    Log.e("NewsRepository", "Error!!")
                    emptyList()
                }
            } catch (t: Throwable) {
                Log.e("NewsRepository", "Request Failed!!", t)
                emptyList()
            }
        }
    }
}

