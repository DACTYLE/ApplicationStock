package com.dactyle.applicationstock

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.drawerlayout.widget.DrawerLayout
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
    lateinit var  actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        actionBarDrawerToggle  = ActionBarDrawerToggle(
            this, drawerLayout, R.string.open_drawer, R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle .syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.about_android_trivia)

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

        FirebaseApp.initializeApp(this)

        /*if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<StockListFragment>(R.id.fragment_container_view)
                setReorderingAllowed(true)
                addToBackStack(null)  // ou un nom si tu le souhaites
            }
        }*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        }else{
            super.onOptionsItemSelected(item)
        }

    }
    /*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }*/
}