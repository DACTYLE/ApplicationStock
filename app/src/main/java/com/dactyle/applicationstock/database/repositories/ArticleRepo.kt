package com.dactyle.applicationstock.database.repositories

import com.dactyle.applicationstock.database.entities.Article
import com.dactyle.applicationstock.database.entities.ArticleDAO
import kotlinx.coroutines.flow.Flow

class ArticleRepo (private val articleDao: ArticleDAO) {

    // Méthode pour obtenir tous les articles
    fun getAllArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles()
    }

    // Méthode pour insérer un article
    fun insert(article: Article) {
        articleDao.insert(article)
    }

    // Méthode pour obtenir les articles par catégorie
    fun getArticlesByCategory(category: String): Flow<List<Article>> {
        return articleDao.getArticlesByCategory(category)
    }
}