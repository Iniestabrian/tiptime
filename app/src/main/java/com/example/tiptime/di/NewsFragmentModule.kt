package com.example.tiptime.di

import com.example.tiptime.Base.BaseFragment
import com.example.tiptime.MainActivity
import com.example.tiptime.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class NewsFragmentModule {

    /*@ContributesAndroidInjector
    abstract fun contributeNewsActivityInjector():MainActivity*/


    @ContributesAndroidInjector
   abstract  fun contributeNewsFragmentInjector(): NewsFragment

}