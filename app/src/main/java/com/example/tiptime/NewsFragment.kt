package com.example.tiptime

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiptime.Base.BaseFragment
import com.example.tiptime.adapters.ArticlesAdapter
import com.example.tiptime.data.ArticleX
import com.example.tiptime.databinding.FragmentNewsBinding
import com.example.tiptime.di.AppModule
import com.example.tiptime.di.MyApplication
import com.example.tiptime.remote.NewsApiService
import com.example.tiptime.repositories.NewsRepository
import com.example.tiptime.viewmodels.NewsViewModel
import dagger.android.support.AndroidSupportInjection



class NewsFragment :BaseFragment<NewsViewModel,FragmentNewsBinding,NewsRepository>() {


   private lateinit var layoutManager: LinearLayoutManager

 // @Inject lateinit var newsViewmodel: NewsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }




    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews()

        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyArticles.layoutManager = layoutManager
        observeArticlesLiveData()

    }

    fun observeArticlesLiveData(){
       viewModel.observeArticlesLiveData().observe(viewLifecycleOwner,object :Observer<List<ArticleX>>{
            override fun onChanged(value: List<ArticleX>) {
                try {
                    val adapter = ArticlesAdapter( requireContext(),value)
                    adapter.notifyDataSetChanged()
                    binding.recyArticles.adapter = adapter

                }catch (e:Exception){
                    Log.e("RecyclerView", "Error setting up adapter: ${e.message}")

                }
            }
        })
}

    override fun getViewModel()= NewsViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )=FragmentNewsBinding.inflate(inflater,container,false)

    override fun getFragmentRepository()= NewsRepository(remoteDataSource.buildApi(NewsApiService::class.java))


}
