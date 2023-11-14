package es.jfp.gallerymodel.activitys

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.HeaderViewListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import es.jfp.gallerymodel.R
import es.jfp.gallerymodel.databinding.ActivityMainBinding
import es.jfp.gallerymodel.fragments.ArtworksViewFragment
import es.jfp.gallerymodel.fragments.LoginFragment
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)

        binding.mainToolbar.inflateMenu(R.menu.main_toolbar_menu)


        setupNavigationDrawer()

        val headerView: View = binding.navigationView.getHeaderView(0)
        setNavHeaderUserdata(headerView)


    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("MissingSuperCall") //This is to avoid the error of not calling the superclass
    override fun onBackPressed() {
        if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupNavigationDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.mainDrawerLayout,
            binding.mainToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        binding.mainDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
        return when(item.itemId) {
            R.id.nav_gallery ->{
                fragmentChanger(ArtworksViewFragment())
                true
            }
            R.id.nav_settings ->{
                Toast.makeText(this, "ñlasnfgñoawri", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_logoff ->{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fragmentChanger(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.main_fragment_container, fragment)
            addToBackStack(null)
            setReorderingAllowed(false)
        }
    }

    private fun setNavHeaderUserdata(view: View) {
        val user: TextView = view.findViewById(R.id.user_logged_nav)
        user.text = LoginFragment.user_logged?.username
    }

}