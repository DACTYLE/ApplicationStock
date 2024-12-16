package com.dactyle.applicationstock.database.repositories

import android.content.Context
import com.dactyle.applicationstock.database.AppDatabase
import com.dactyle.applicationstock.database.http.ArticleApiService
import com.dactyle.applicationstock.database.repositories.sources.RetrofitArticleDataSource
import com.dactyle.applicationstock.database.repositories.sources.RoomArticleDataSource
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SourcesFactory {
    companion object Factory {

        fun getSourceFromId(sourceId:String, context: Context) : ArticleDataSource {
            var originDataSource:ArticleDataSource

            if(sourceId=="HTTP") {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://firestore.googleapis.com/v1/projects/applicationstock-86cf7/databases/(default)/documents/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
                val apiService = retrofit.create(ArticleApiService::class.java)
                originDataSource = RetrofitArticleDataSource(apiService)
            }
            else {
                val database = AppDatabase.getDatabase(context)
                originDataSource= RoomArticleDataSource(database.articleDao());
            }
            return originDataSource;
        }
    }
}