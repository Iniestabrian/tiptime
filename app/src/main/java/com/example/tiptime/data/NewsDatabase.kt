package com.example.tiptime.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [ArticleR::class], version = 2, exportSchema = false)

 abstract class NewsDatabase : RoomDatabase(){

    abstract fun articleDao(): ArticlesDao

    companion object {
       const val DATABASE_NAME = "News_database"
    }







}
