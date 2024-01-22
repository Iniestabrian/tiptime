package com.example.tiptime.di

import com.example.tiptime.remote.NewsApiService
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

    /*@Singleton
    @Provides
    fun provideBaseRepository(newsApiService: NewsApiService): BaseRepository {
        return NewsRepository(newsApiService)
    }*/





}



//Note.....You cannot create an instance of an abstruct class
//the commenteed code works because it bypasses the rule by returning Newsrepository which extends Baserepository

