package com.dactyle.applicationstock

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.dactyle.applicationstock.ui.StockListFragment
import com.google.firebase.FirebaseApp


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        FirebaseApp.initializeApp(this)

        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overmenu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_settings -> {
                        // Gérer l'option de menu Settings
                        true
                    }
                    R.id.action_about -> {
                        // Gérer l'option de menu About
                        true
                    }
                    else -> false
                }
            }
        }, this)

        /*if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<StockListFragment>(R.id.fragment_container_view)
                setReorderingAllowed(true)
                addToBackStack(null)  // ou un nom si tu le souhaites
            }
        }*/
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}