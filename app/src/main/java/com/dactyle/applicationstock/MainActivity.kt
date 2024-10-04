package com.dactyle.applicationstock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.dactyle.applicationstock.ui.StockListFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<StockListFragment>(R.id.fragment_container_view)
                setReorderingAllowed(true)
                addToBackStack(null)  // ou un nom si tu le souhaites
            }
        }

        /*if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<ArticleFragmentRepository>(R.id.fragment_container_view)
                setReorderingAllowed(true)
                addToBackStack(null)  // ou un nom si tu le souhaites
            }
        }*/
    }
}