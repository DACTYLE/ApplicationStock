package com.dactyle.applicationstock.database.repositories.sources

import com.dactyle.applicationstock.database.entities.Article
import com.dactyle.applicationstock.database.http.ArticleApiService
import com.dactyle.applicationstock.database.repositories.ArticleDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RetrofitArticleDataSource(private val apiService: ArticleApiService) : ArticleDataSource {

    override fun getAllArticles(): Flow<List<Article>> = flow {
            val response = apiService.getAllArticles()
            emit(response.toArticles()) // Conversion automatique grâce à la méthode toArticles()
        }

}
