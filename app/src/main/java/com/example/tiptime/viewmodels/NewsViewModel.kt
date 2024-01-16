package com.example.tiptime.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tiptime.data.ArticleX
import com.example.tiptime.data.ArticlesList
import com.example.tiptime.remote.NewsApiService
import com.example.tiptime.remote.RetrofitInstance
import com.example.tiptime.repositories.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor (private val repository: NewsRepository) :ViewModel(){




    private  var articlesLiveData = MutableLiveData<List<ArticleX>>()





    fun getNews() {
        viewModelScope.launch {
            val articles = repository.getNews()
            articlesLiveData.value = articles
        }
    }

    fun observeArticlesLiveData(): LiveData<List<ArticleX>> {
        return articlesLiveData
    }













/*

    fun getNews() {
       newsRepository.getNews()
    }
*/


/*

    fun observeArticlesLiveData(): LiveData<List<ArticleX>> {
        return newsRepository.getArticlesLiveData()
    }
*/


}