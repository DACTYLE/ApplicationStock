package com.dactyle.applicationstock.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dactyle.applicationstock.R
import com.dactyle.applicationstock.database.entities.Article

class StockRecycleAdapter : ListAdapter<Article, StockRecycleAdapter.ArticleViewHolderRep>(StockDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolderRep {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleViewHolderRep(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolderRep, position: Int) {
        val article = getItem(position) // Utiliser getItem() pour obtenir l'article
        holder.bind(article)
    }

    class ArticleViewHolderRep(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)

        fun bind(article: Article) {
            titleTextView.text = article.title
            contentTextView.text = article.content
        }
    }
}

class StockDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id // Comparaison des IDs
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem // Comparaison des contenus
    }
}