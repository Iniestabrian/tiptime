package com.example.di

import com.example.tiptime.remote.NewsApiService
import com.example.tiptime.remote.RetrofitInstance
import com.example.tiptime.repositories.NewsRepository
import com.example.tiptime.viewmodels.NewsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun  providesAuthApi(remoteDataSource:RetrofitInstance):NewsApiService {
        return remoteDataSource.buildApi(NewsApiService::class.java)
    }

}