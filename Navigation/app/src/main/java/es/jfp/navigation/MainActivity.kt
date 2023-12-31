package es.jfp.navigation

import android.annotation.SuppressLint
import android.graphics.Camera
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationView
import es.jfp.navigation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.myToolbar)

        setUpNavigationDrawer()
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
                supportFragmentManager.commit {
                    replace<CameraFragment>(R.id.main_fragment_container)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
                true
            }
            R.id.nav_gallery -> {
                supportFragmentManager.commit {
                    replace<GalleryFragment>(R.id.main_fragment_container)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
                true
            }
            R.id.nav_tools -> {
                supportFragmentManager.commit {
                    replace<ToolsFragment>(R.id.main_fragment_container)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
                true
            }
            R.id.nav_share -> {
                supportFragmentManager.commit {
                    replace<ShareFragment>(R.id.main_fragment_container)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
                true
            }
            R.id.nav_send -> {
                supportFragmentManager.commit {
                    replace<SendFragment>(R.id.main_fragment_container)
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
                true
            }
            else -> false
        }
    }


}