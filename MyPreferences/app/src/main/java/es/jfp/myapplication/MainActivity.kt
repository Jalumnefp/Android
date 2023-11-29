package es.jfp.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import es.jfp.myapplication.databinding.ActivityMainBinding

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

        return when (item.itemId) {
            R.id.nav_camera -> {
                Snackbar.make(binding.root, "Camera Fragment", Snackbar.LENGTH_SHORT).show()
                true
            }
            R.id.nav_gallery -> {
                Snackbar.make(binding.root, "Gallery Fragment", Snackbar.LENGTH_SHORT).show()
                true
            }
            R.id.nav_tools -> {
                fragmentChanger(SettingsFragment())
                true
            }
            R.id.nav_share -> {
                Snackbar.make(binding.root, "Share Fragment", Snackbar.LENGTH_SHORT).show()
                true
            }
            R.id.nav_send -> {
                Snackbar.make(binding.root, "Send Fragment", Snackbar.LENGTH_SHORT).show()
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
}