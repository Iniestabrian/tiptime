package com.example.tiptime.di

import android.content.Context
import com.example.tiptime.MainActivity
import dagger.BindsInstance
import dagger.Component
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
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, NewsFragmentModule::class,RepositoryModule::class,ContextModule::class])
interface MyComponent : AndroidInjector<MyApplication> {

/*    @Component.Builder
    interface Builder {
       *//* @BindsInstance
        fun context(context: Context): Builder*//*
        fun build(): MyComponent
    }*/

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MyApplication,contextModule: ContextModule): MyComponent
    }

    // Add the correct method for injecting MainActivity
    fun inject(activity: MainActivity)

}


