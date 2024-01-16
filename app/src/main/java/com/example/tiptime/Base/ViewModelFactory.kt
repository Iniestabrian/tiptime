package com.example.tiptime.Base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tiptime.repositories.BaseRepository
import com.example.tiptime.repositories.NewsRepository
import com.example.tiptime.viewmodels.NewsViewModel
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor( private  val repository: BaseRepository): ViewModelProvider.NewInstanceFactory() {

    @Override
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return  when{
            modelClass.isAssignableFrom(NewsViewModel::class.java) ->NewsViewModel(repository as NewsRepository) as T
            else->throw IllegalArgumentException("ViewModel class not found")

        }
    }

}
