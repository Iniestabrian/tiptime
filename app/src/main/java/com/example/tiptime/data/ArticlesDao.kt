package com.example.tiptime.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Flowable


@Dao
interface ArticlesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<ArticleR>)

    @Query("SELECT * FROM News_table")
    fun gettArticles(): Flowable<List<ArticleR>>

    @Query("SELECT * FROM News_table")
    fun getArticles(): LiveData<List<ArticleR>>
}
