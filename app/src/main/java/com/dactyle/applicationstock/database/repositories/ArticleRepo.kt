package com.dactyle.applicationstock.database.repositories

import com.dactyle.applicationstock.database.entities.Article
import com.dactyle.applicationstock.database.entities.ArticleDAO
import kotlinx.coroutines.flow.Flow

class ArticleRepo (private val articleSrc: ArticleDataSource) {

    // Méthode pour obtenir tous les articles
    fun getAllArticles(): Flow<List<Article>> {
        return articleSrc.getAllArticles()
    }

    // Méthode pour insérer un article
    fun insert(article: Article) {
        articleSrc.insert(article)
    }

    // Méthode pour obtenir les articles par catégorie
    fun getArticlesByCategory(category: String): Flow<List<Article>> {
        return articleSrc.getArticlesByCategory(category)
    }
}