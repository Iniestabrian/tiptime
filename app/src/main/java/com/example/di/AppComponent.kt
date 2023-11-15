package com.example.di

import com.example.MyApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class,NewsFragmentModule::class,AppModule::class])
interface AppComponent {

    fun inject(application: MyApplication)


}