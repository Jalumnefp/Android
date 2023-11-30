package es.jfp.myapplication.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import es.jfp.myapplication.R
import es.jfp.myapplication.databinding.ActivityMainBinding
import es.jfp.myapplication.databinding.NavHeaderBinding
import es.jfp.navigation.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.myToolbar)

        setUpNavigationDrawer()

        val username = binding.navigationView.getHeaderView(0).findViewById<TextView>(R.id.header_title)
        if (username != null) {
            username.text = getUsernamePreference()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_save ->{
                Toast.makeText(this, getString(R.string.save), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings ->{
                fragmentChanger(SettingsFragment())
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("MissingSuperCall") //This is to avoid the error of not calling the superclass
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setUpNavigationDrawer(){
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.myToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //Close the navigation drawer
        binding.drawerLayout.closeDrawer(GravityCompat.START)

        return when(item.itemId){
            R.id.nav_camera -> {
                fragmentChanger(CameraFragment())
                true
            }
            R.id.nav_gallery -> {
                fragmentChanger(GalleryFragment())
                true
            }
            R.id.nav_tools -> {
                fragmentChanger(ToolsFragment())
                true
            }
            R.id.nav_share -> {
                fragmentChanger(ShareFragment())
                true
            }
            R.id.nav_send -> {
                fragmentChanger(SendFragment())
                true
            }
            else -> false
        }
    }

    private fun fragmentChanger(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.main_fragment_container, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun getUsernamePreference(): String {
        val prefs = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE)
        val username = prefs.getString("USERNAME_LOGGED", null)
        return username ?: ""
    }


}