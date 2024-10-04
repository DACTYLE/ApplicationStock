package com.dactyle.applicationstock.database

import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.content.Context
import com.dactyle.applicationstock.database.entities.Article

class DatabaseCallback(private val context: Context) : RoomDatabase.Callback() {


    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Log.d("DatabaseCallback", "Database created")

        // Lancer les opérations en arrière-plan pour insérer les articles par défaut
        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getDatabase(context)


            val articleDao = database.articleDao()

            // Insérer les données par défaut dans la base de données
            articleDao.insert(Article(title = "Premier Article", content = "Contenu du premier article", category = "General"))
            articleDao.insert(Article(title = "Deuxième Article", content = "Contenu du deuxième article", category = "Sport"))
            articleDao.insert(Article(title = "Troisième Article", content = "Contenu du troisième article", category = "Technologie"))

            Log.d("DatabaseCallback", "Default articles inserted")
        }
    }


    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        Log.d("DatabaseCallback", "Database opened")
        // Cette méthode est appelée chaque fois que la base de données est ouverte
        // Vous pouvez exécuter des opérations ici si nécessaire
    }
}