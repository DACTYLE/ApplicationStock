package com.dactyle.applicationstock.database.repositories

import androidx.lifecycle.LiveData
import com.dactyle.applicationstock.database.entities.Article
import com.dactyle.applicationstock.database.entities.ArticleDAO

class ArticleRepo (private val articleDao: ArticleDAO) {

    // Méthode pour obtenir tous les articles
    suspend fun getAllArticles(): List<Article> {
        return articleDao.getAllArticles()
    }

    // Méthode pour insérer un article
    suspend fun insert(article: Article) {
        articleDao.insert(article)
    }

    // Méthode pour obtenir les articles par catégorie
    suspend fun getArticlesByCategory(category: String): List<Article> {
        return articleDao.getArticlesByCategory(category)
    }
}