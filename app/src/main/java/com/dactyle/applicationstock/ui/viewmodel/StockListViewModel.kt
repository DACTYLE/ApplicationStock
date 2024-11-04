package com.dactyle.applicationstock.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dactyle.applicationstock.database.entities.Article
import com.dactyle.applicationstock.database.repositories.ArticleRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class StockListViewModel (private val articleRepository: ArticleRepo) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>(emptyList())
    val articles: LiveData<List<Article>> get() = _articles

    private val _articlesByCategory = MutableLiveData<List<Article>>()
    val articlesByCategory: LiveData<List<Article>> get() = _articlesByCategory

    // Récupérer tous les articles dans une coroutine
    fun fetchArticles() {
        viewModelScope.launch {
            articleRepository.getAllArticles() // Retourne un Flow
                .catch { e ->
                    // Gestion des erreurs si nécessaire
                    Log.e("ArticleViewModel", "Erreur lors de la récupération des articles", e)
                }
                .collect { fetchedArticles ->
                    _articles.value = fetchedArticles // Mise à jour du StateFlow
                }
        }
    }

    // Récupérer les articles par catégorie dans une coroutine
    fun fetchArticlesByCategory(category: String) {
        viewModelScope.launch {
            articleRepository.getArticlesByCategory(category) // Retourne un Flow
                .catch { e ->
                    // Gestion des erreurs
                    Log.e("ArticleViewModel", "Erreur lors de la récupération des articles par catégorie", e)
                }
                .collect { fetchedArticlesByCategory ->
                    _articlesByCategory.value = fetchedArticlesByCategory // Mise à jour du StateFlow
                }
        }
    }

    // Méthode pour insérer un article
    fun insertArticle(article: Article) {
        viewModelScope.launch {
            articleRepository.insert(article) // Appel suspendu
        }
    }
}