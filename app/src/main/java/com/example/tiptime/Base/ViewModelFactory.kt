/*
package com.example.tiptime.Base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tiptime.repositories.BaseRepository
import com.example.tiptime.repositories.NewsRepository
import com.example.tiptime.viewmodels.NewsViewModel

class ViewModelFactory( private  val repository: BaseRepository): ViewModelProvider.NewInstanceFactory() {

    @Override
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return  when{
            modelClass.isAssignableFrom(NewsViewModel::class.java) ->NewsViewModel(repository as NewsRepository) as T
            else->throw IllegalArgumentException("ViewModel class not found")

        }
    }
}*/
