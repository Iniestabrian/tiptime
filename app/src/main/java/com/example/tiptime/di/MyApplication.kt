package com.example.tiptime.di

import android.app.Application
import dagger.android.AndroidInjection.inject
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MyApplication : DaggerApplication(){

    @Inject
    lateinit var mInjector: DispatchingAndroidInjector<Any>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val context = applicationContext
        val contextModule = ContextModule(context)
        return DaggerMyComponent.factory()
            .create(this,contextModule)
            .apply {
                inject(this@MyApplication)
            }
       /* return DaggerMyComponent.create().apply {
            inject(this@MyApplication)
        }*/
    }



    override fun onCreate() {
        super.onCreate()
        /*DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .inject(this)*/

    }


}

