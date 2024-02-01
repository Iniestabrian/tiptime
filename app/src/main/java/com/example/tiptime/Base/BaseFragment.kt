package com.example.tiptime.Base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.tiptime.remote.RetrofitInstance
import com.example.tiptime.repositories.BaseRepository
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM: ViewModel, B: ViewBinding,R: BaseRepository> :DaggerFragment(){


    protected lateinit var binding:B

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var remoteDataSource :RetrofitInstance

    protected lateinit var viewModel: VM
    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = getFragmentBinding(inflater,container)

       /*val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this,factory)[getViewModel()]*/

        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModel())
        return  binding.root


    }

    abstract  fun getViewModel(): Class<VM>
    abstract  fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B
   // abstract  fun getFragmentRepository():R


}
