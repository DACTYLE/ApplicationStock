package com.dactyle.applicationstock.database.http.model

data class FirestoreDocument(
    val name: String,
    val fields: FirestoreFields,
    val createTime: String,
    val updateTime: String
)

data class FirestoreFields(
    val title: FirestoreValue,
    val content: FirestoreValue,
    val category: FirestoreValue
)

data class FirestoreValue(
    val stringValue: String
)