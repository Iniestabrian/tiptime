package com.example.tiptime.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tiptime.data.ArticlesDao
import com.example.tiptime.data.NewsDatabase
import   com.example.tiptime.remote.NewsApiService
import com.example.tiptime.remote.RetrofitInstance
import com.example.tiptime.repositories.BaseRepository
import com.example.tiptime.repositories.NewsRepository
import com.example.tiptime.viewmodels.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.Multibinds
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun  providesAuthApi(remoteDataSource:RetrofitInstance):NewsApiService {
        return remoteDataSource.buildApi(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context:Context): NewsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            NewsDatabase.DATABASE_NAME
        )

            .fallbackToDestructiveMigration()
           // .addMigrations(MIGRATION_1_2)
            .build()
    }


    @Singleton
    @Provides
     fun providesArticlesDao(database: NewsDatabase): ArticlesDao {
       return  database.articleDao()

    }



  /*  @Singleton
    @Provides
    fun provideBaseRepository(newsApiService: NewsApiService): BaseRepository {
        return NewsRepository(newsApiService)
    }*/


}



//Note.....You cannot create an instance of an abstruct class
//the commenteed code works because it bypasses the rule by returning Newsrepository which extends Baserepository

