package com.dactyle.applicationstock.database.repositories

import com.dactyle.applicationstock.database.entities.Article
import kotlinx.coroutines.flow.Flow

interface ArticleDataSource {
    // Obtenir les articles
    fun getAllArticles(): Flow<List<Article>>


    // Implémentation par défaut
    fun getArticlesByCategory(category: String): Flow<List<Article>> {
        throw UnsupportedOperationException("Méthode non implémentée")
    }

    // Implémentation par défaut
    fun insert(article: Article) {
        throw UnsupportedOperationException("Méthode non implémentée")
    }
}