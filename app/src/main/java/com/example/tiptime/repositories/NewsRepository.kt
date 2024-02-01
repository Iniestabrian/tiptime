package com.example.tiptime.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tiptime.data.ArticleR
import com.example.tiptime.data.ArticleX
import com.example.tiptime.data.ArticlesDao
import com.example.tiptime.data.ArticlesList
import com.example.tiptime.data.Source
/*
import com.example.tiptime.data.ArticlesDao
*/
import com.example.tiptime.remote.NewsApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class NewsRepository @Inject constructor (private val api: NewsApiService, private val articlesDao: ArticlesDao) : BaseRepository() {

  // private val retrofitInstance = RetrofitInstance()

    private val apiKey = "c9242f1de0b14fddb655956487b707f3"

    /*suspend fun getNews(): List<ArticleR> {
        return withContext(Dispatchers.IO) {
            try {
                //val apiService = retrofitInstance.buildApi(NewsApiService::class.java)
                val response = api.getNews("us", "technology", 20, apiKey)
               // val resp = safeApiCall { api.getNews("us", "technology", 20, apiKey)}
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
    }*/

    suspend fun getNews() = safeApiCall {
        api.getNews("us", "technology", 20, apiKey)
    }

   /* fun getAllArticles(): Flowable<List<ArticleR>> {
        return articlesDao.getArticles()
            .subscribeOn(Schedulers.io())
    }*/


    fun insertArticles(articlesList: ArticlesList): Completable {
        return Completable.fromAction {
            val articleEntities = articlesList.articles.map { articleX ->
                ArticleR(
                    author = articleX.author,
                    content = articleX.content,
                    description = articleX.description,
                    publishedAt = articleX.publishedAt,
                    sourceId = articleX.source.id?.toString() ?: "Unknown",
                    sourceName = articleX.source.name,
                    title = articleX.title,
                    url = articleX.url,
                    urlToImage = articleX.urlToImage
                )
            }
            articlesDao.insertArticles(articleEntities)
        }.subscribeOn(Schedulers.io())
    }


    // In your repository
    fun getAllArticles(): LiveData<List<ArticleX>> {
        val articlesLiveData = MutableLiveData<List<ArticleX>>()
        val disposable = CompositeDisposable()

        disposable.add(
            articlesDao.getArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { articles ->
                    articles.map { articleR ->
                        // Convert ArticleR to ArticleX
                        ArticleX(
                            author = articleR.author!!,
                            content = articleR.content!!,
                            description = articleR.description!!,
                            publishedAt = articleR.publishedAt!!,
                            source = Source(
                                id = articleR.sourceId,
                                name = articleR.sourceName!!
                            ),
                            title = articleR.title!!,
                            url = articleR.url!!,
                            urlToImage = articleR.urlToImage!!
                        )
                    }
                }
                .subscribe({ transformedArticles ->
                    // Update the MutableLiveData with transformed articles
                    articlesLiveData.value = transformedArticles
                }, { error ->
                    // Handle any errors
                })
        )

        return articlesLiveData
    }





}



