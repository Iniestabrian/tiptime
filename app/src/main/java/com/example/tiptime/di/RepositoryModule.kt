package com.example.tiptime.di

import com.example.tiptime.repositories.BaseRepository
import com.example.tiptime.repositories.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.multibindings.Multibinds
import javax.inject.Singleton


@Module
abstract class RepositoryModule {
    /*
        @Multibinds
        abstract fun bindBaseRepositories(): Set<BaseRepository>*/

    @Singleton
    @Binds
    abstract fun bindBaseRepository(repository: NewsRepository): BaseRepository

}