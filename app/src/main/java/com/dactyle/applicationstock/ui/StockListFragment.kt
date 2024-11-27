package com.dactyle.applicationstock.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dactyle.applicationstock.R
import com.dactyle.applicationstock.database.AppDatabase
import com.dactyle.applicationstock.database.entities.Article
import com.dactyle.applicationstock.database.http.ArticleApiService
import com.dactyle.applicationstock.database.repositories.ArticleRepo
import com.dactyle.applicationstock.database.repositories.sources.RetrofitArticleDataSource
import com.dactyle.applicationstock.database.repositories.sources.RoomArticleDataSource
import com.dactyle.applicationstock.ui.adapters.StockRecycleAdapter
import com.dactyle.applicationstock.ui.viewmodel.StockListViewModel
import com.dactyle.applicationstock.ui.viewmodel.StockListViewModelFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        //val database = AppDatabase.getDatabase(requireContext())
        //val originDataSource = RoomArticleDataSource(database.articleDao());

        val retrofit = Retrofit.Builder()
            .baseUrl("https://firestore.googleapis.com/v1/projects/applicationstock-86cf7/databases/(default)/documents/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        val apiService = retrofit.create(ArticleApiService::class.java)
        val originDataSource = RetrofitArticleDataSource(apiService)

        val articleRepository = ArticleRepo(originDataSource)

        viewModel = ViewModelProvider(this, StockListViewModelFactory(articleRepository)).get(StockListViewModel::class.java)

        // Observer les articles et les mettre à jour dans le RecyclerView
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            Log.d("ArticleFragment", "Articles fetched: ${articles.size}") // Vérifie le nombre d'articles
            updateRecyclerView(articles)
        }
        /*viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.articles.collect { articles ->
                    Log.d("ArticleFragment", "Articles fetched: ${articles.size}") // Vérifie le nombre d'articles
                    updateRecyclerView(articles)
                }
            }
        }*/
        viewModel.fetchArticles()

    }

    private fun updateRecyclerView(articles: List<Article>) {
        // Mise à jour du RecyclerView avec les articles récupérés
        SRAdapter.submitList(articles)
    }
}