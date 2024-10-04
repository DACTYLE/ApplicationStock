package com.dactyle.applicationstock.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dactyle.applicationstock.database.entities.Article
import com.dactyle.applicationstock.database.repositories.ArticleRepo
import kotlinx.coroutines.launch

class StockListViewModel (private val articleRepository: ArticleRepo) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    private val _articlesByCategory = MutableLiveData<List<Article>>()
    val articlesByCategory: LiveData<List<Article>> get() = _articlesByCategory

    // Récupérer tous les articles dans une coroutine
    fun fetchArticles() {
        viewModelScope.launch {
            try {
                val fetchedArticles = articleRepository.getAllArticles() // Appel suspendu
                _articles.postValue(fetchedArticles) // Mise à jour de la LiveData
            } catch (e: Exception) {
                // Gestion des erreurs
                Log.e("ArticleViewModel", "Erreur lors de la récupération des articles", e)
            }
        }
    }

    // Récupérer les articles par catégorie dans une coroutine
    fun fetchArticlesByCategory(category: String) {
        viewModelScope.launch {
            _articlesByCategory.value = articleRepository.getArticlesByCategory(category) // Appel suspendu
        }
    }

    // Méthode pour insérer un article
    fun insertArticle(article: Article) {
        viewModelScope.launch {
            articleRepository.insert(article) // Appel suspendu
        }
    }
}