package com.apolis.booklibararyhwday17.views.activities

import FragmentAddAuthors
import FragmentAuthors
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.apolis.booklibararyhwday17.R
import com.apolis.booklibararyhwday17.databinding.ActivityMainBinding
import com.apolis.booklibararyhwday17.views.fragments.FragmentAddBook
import com.apolis.booklibararyhwday17.views.fragments.FragmentHome
import com.apolis.booklibararyhwday17.views.fragments.FragmentLibrary
import com.apolis.booklibararyhwday17.views.fragments.FragmentProfile

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){
            if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }else{
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_menu_24)
        }

        supportFragmentManager.beginTransaction().replace(R.id.main_container,
            FragmentHome()).commit()

        binding.bottomAppBar.setOnItemSelectedListener { menuItems ->
            menuItems.isChecked = true
            when(menuItems.itemId){
                R.id.home -> handleMenuEvent(FragmentHome())
                R.id.library -> handleMenuEvent(FragmentLibrary())
                R.id.authors -> handleMenuEvent(FragmentAuthors())
                R.id.profile -> handleMenuEvent(FragmentProfile())
            }

            true
        }

        binding.navViews.setNavigationItemSelectedListener { menuItems ->
            menuItems.isChecked = true
            when(menuItems.itemId){
                R.id.addNewBook -> handleMenuEvent(FragmentAddBook())
                R.id.addNewAuthor -> handleMenuEvent(FragmentAddAuthors())
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun handleMenuEvent(fragment: Fragment) {

        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()

    }
}