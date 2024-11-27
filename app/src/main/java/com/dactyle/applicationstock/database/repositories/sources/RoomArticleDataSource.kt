package com.dactyle.applicationstock.database.repositories.sources

import com.dactyle.applicationstock.database.entities.Article
import com.dactyle.applicationstock.database.entities.ArticleDAO
import com.dactyle.applicationstock.database.repositories.ArticleDataSource
import kotlinx.coroutines.flow.Flow

class RoomArticleDataSource (private val articleDao: ArticleDAO) : ArticleDataSource {

    override fun getAllArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles()
    }

    override fun getArticlesByCategory(category: String):  Flow<List<Article>> {
        return articleDao.getArticlesByCategory(category)
    }

    override fun insert(article: Article) {
        articleDao.insert(article)
    }

}