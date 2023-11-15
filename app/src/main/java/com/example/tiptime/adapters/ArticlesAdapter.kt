package com.example.tiptime.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.tiptime.R
import com.example.tiptime.data.ArticleX
import com.example.tiptime.data.ArticlesList


class ArticlesAdapter(val context: Context, val articlesList: List<ArticleX>)  : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>(){



  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
   var itemView = LayoutInflater.from(context).inflate(R.layout.articles_design,parent,false)
   return ViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
   val article = articlesList[position]

   holder.textTitle.text = article.title
   holder.textDescription.text = article.description
      holder.source.text = article.source.name

      holder.itemView.setOnClickListener {

      }


   Glide.with(holder.itemView)
    .load(article.urlToImage)
    .placeholder(R.drawable.expenses)
    .transition(DrawableTransitionOptions.withCrossFade())
    .into(holder.imageView)
  }

  override fun getItemCount(): Int {
   return articlesList.size
  }

  class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

   val imageView: ImageView = itemView.findViewById(R.id.imageView)
   val textTitle: TextView = itemView.findViewById(R.id.textTitle)
   val textDescription: TextView = itemView.findViewById(R.id.textDescription)
      val source: TextView = itemView.findViewById(R.id.textSource)


  }

 }