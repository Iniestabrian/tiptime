package com.example.tiptime.di

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApplication : DaggerApplication(){

    @Inject
    lateinit var mInjector: DispatchingAndroidInjector<Any>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.create().apply {
            inject(this@MyApplication)
        }
    }



    override fun onCreate() {
        super.onCreate()
        /*DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .inject(this)*/

    }


}

