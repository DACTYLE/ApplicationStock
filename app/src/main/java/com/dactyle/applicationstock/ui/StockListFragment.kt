package com.dactyle.applicationstock.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dactyle.applicationstock.R
import com.dactyle.applicationstock.database.AppDatabase
import com.dactyle.applicationstock.database.entities.Article
import com.dactyle.applicationstock.database.repositories.ArticleRepo
import com.dactyle.applicationstock.ui.adapters.StockRecycleAdapter
import com.dactyle.applicationstock.ui.viewmodel.StockListViewModel
import com.dactyle.applicationstock.ui.viewmodel.StockListViewModelFactory

class StockListFragment : Fragment() {
    private lateinit var viewModel: StockListViewModel
    private lateinit var SRAdapter: StockRecycleAdapter

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.stocklist_fragment, container, false)

        // Initialiser le RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)

        SRAdapter = StockRecycleAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SRAdapter
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = AppDatabase.getDatabase(requireContext())
        val articleRepository = ArticleRepo(database.articleDao())

        viewModel = ViewModelProvider(this, StockListViewModelFactory(articleRepository)).get(StockListViewModel::class.java)

        // Observer les articles et les mettre à jour dans le RecyclerView
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            Log.d("ArticleFragment", "Articles fetched: ${articles.size}") // Vérifie le nombre d'articles
            updateRecyclerView(articles)
        }

        viewModel.fetchArticles()

    }

    private fun updateRecyclerView(articles: List<Article>) {
        // Mise à jour du RecyclerView avec les articles récupérés
        SRAdapter.submitList(articles)
    }
}