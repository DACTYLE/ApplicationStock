package com.dactyle.applicationstock.database.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<Article>

    @Query("SELECT * FROM articles WHERE category = :category")
    suspend fun getArticlesByCategory(category: String): List<Article>
}