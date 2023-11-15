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
import com.example.tiptime.adapters.ArticlesAdapter
import com.example.tiptime.data.ArticleX
import com.example.tiptime.databinding.FragmentNewsBinding
import com.example.tiptime.viewmodels.NewsViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

   private lateinit var layoutManager: LinearLayoutManager



   private lateinit var newsViewmodel: NewsViewModel





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentNewsBinding.inflate(inflater,container,false)

        newsViewmodel = ViewModelProvider(this)[NewsViewModel::class.java]

        newsViewmodel.getNews()




        return binding.root
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyArticles.layoutManager = layoutManager
        observeArticlesLiveData()




    }



    fun observeArticlesLiveData(){
        newsViewmodel.observeArticlesLiveData().observe(viewLifecycleOwner,object :Observer<List<ArticleX>>{
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




}}