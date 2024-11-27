package com.dactyle.applicationstock.database.http.model

import com.dactyle.applicationstock.database.entities.Article

data class FirestoreResponse(
    val documents: List<FirestoreDocument>
)
{
    // Convertir les documents en une liste d'articles
    fun toArticles(): List<Article> {
        return documents.map { document ->
            Article(
                title = document.fields.title.stringValue,
                content = document.fields.content.stringValue,
                category = document.fields.category.stringValue
            )
        }
    }
}