package com.example.tiptime.di

import com.example.tiptime.MainActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/*
@Singleton

@Component(modules = [ AndroidInjectionModule::class, NewsFragmentModule::class, AppModule::class])
interface AppComponent {

    fun inject(application: MyApplication)

}*/





@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, NewsFragmentModule::class,RepositoryModule::class])
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
    }

    // Add the correct method for injecting MainActivity
    fun inject(activity: MainActivity)
}


