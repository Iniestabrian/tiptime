package com.example.tiptime.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.data.ArticleX
import com.example.tiptime.remote.Resource
import com.example.tiptime.repositories.NewsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor (private val repository: NewsRepository) :ViewModel() {


    private var articlesLiveData = MutableLiveData<List<ArticleX>>()


    fun observeRoomsLiveData() {
        repository.getAllArticles().observeForever { articles ->
            // Log the articles
            Log.d("RoomDBOfflineData", "Articles: $articles")
            // Update the LiveData if needed
            articlesLiveData.value = articles
        }
    }


    fun getNews() {
        viewModelScope.launch {
            when (val result = repository.getNews()) {
                is Resource.Success -> {
                    // Handle successful result
                    val articles = result.value.articles


                    articlesLiveData.value = articles

                    val result = result.value
                    repository.insertArticles(result)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({

                            Log.e("RoomDB", "Data inserted to room")

                        }, { error ->
                            // Handle insertion error
                            Log.e("RoomDB", "Could not insert data to room")

                        })
                    // Do something with the articles
                   // Log.e("RoomDB",observeRoomsLiveData().toString())
                }


                is Resource.Failure -> {
                    // Handle failure
                    if (result.isNetworkError) {
                        // Handle network error
                        observeRoomsLiveData()
                       // Log.e("RoomDBData", articlesLiveData.value.toString())

                    } else {
                        // Handle other errors
                        val errorCode = result.errorCode
                        val errorBody = result.errorBody

                        Log.e("RoomDBError", errorBody.toString())

                        // Do something with errorCode and errorBody
                    }
                }


                else -> {}
            }


        }

    }

    fun observeArticlesLiveData(): LiveData<List<ArticleX>> {
        return articlesLiveData
    }

    /*// Function to fetch articles and observe them
    fun getAllArticles(): LiveData<List<ArticleX>> {


        return repository.getAllArticles()
    }*/


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


