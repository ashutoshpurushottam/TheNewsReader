package com.eigendaksh.thenewsreader.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.eigendaksh.thenewsreader.R
import com.eigendaksh.thenewsreader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG by lazy { MainActivity::class.java.simpleName }

    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        //NavHostFragment needs to be updated with a new nav_graph when you have more than 1 graphs
        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        //This will make our navController accessible from any fragment where we have a reference to mainActivity
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
        binding.bottomNavView.setupWithNavController(navController)
    }




    override fun onSupportNavigateUp() =
        findNavController(R.id.navHostFragment).navigateUp()


}
