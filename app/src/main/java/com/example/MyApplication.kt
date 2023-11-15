package com.example

import android.app.Application
import com.example.di.AppComponent
import com.example.di.AppModule
import com.example.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApplication : Application(),HasAndroidInjector{

    @Inject
    lateinit var mInjector: DispatchingAndroidInjector<Any>


    @Override
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
            .inject(this)


    }


    override fun androidInjector(): AndroidInjector<Any> {
    return mInjector
    }

}