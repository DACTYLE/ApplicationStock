package com.dactyle.applicationstock.database.http

import com.dactyle.applicationstock.database.entities.Article
import com.dactyle.applicationstock.database.http.model.FirestoreResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ArticleApiService {
    @GET("Stock")
    suspend fun getAllArticles() : FirestoreResponse
}