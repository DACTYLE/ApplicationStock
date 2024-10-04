package com.dactyle.applicationstock.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import android.util.Log
import com.dactyle.applicationstock.database.entities.Article
import com.dactyle.applicationstock.database.entities.ArticleDAO

@Database(entities = [Article::class], version = AppDatabase.DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDAO

    companion object {
        const val DATABASE_VERSION = 2
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            Log.d("AppDatabase", "get Database")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "stock_database"
                ).fallbackToDestructiveMigration().addCallback(DatabaseCallback(context)).build()
                INSTANCE = instance
                instance
            }
        }

    }
}