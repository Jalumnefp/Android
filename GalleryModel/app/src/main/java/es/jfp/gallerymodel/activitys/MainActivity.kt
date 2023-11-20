package es.jfp.gallerymodel.activitys

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.graphics.drawable.GradientDrawable.Orientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.HeaderViewListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import es.jfp.gallerymodel.R
import es.jfp.gallerymodel.databinding.ActivityMainBinding
import es.jfp.gallerymodel.databinding.FragmentArtDetailBinding
import es.jfp.gallerymodel.dialogs.LogoffDialogFragment
import es.jfp.gallerymodel.fragments.ArtDetailFragment
import es.jfp.gallerymodel.fragments.ArtDetailFragment.Companion.ARG_AUTHOR
import es.jfp.gallerymodel.fragments.ArtDetailFragment.Companion.ARG_IMAGE
import es.jfp.gallerymodel.fragments.ArtDetailFragment.Companion.ARG_TITLE
import es.jfp.gallerymodel.fragments.ArtworksViewFragment
import es.jfp.gallerymodel.fragments.LoginFragment
import es.jfp.gallerymodel.fragments.SettingsFragment
import org.w3c.dom.Text
import kotlin.math.log

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private var isPortrait: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)

        binding.mainToolbar.inflateMenu(R.menu.main_toolbar_menu)
        binding.mainFragmentContainer2?.visibility = View.INVISIBLE
        setupNavigationDrawer()

        val headerView: View = binding.navigationView.getHeaderView(0)
        setNavHeaderUserdata(headerView)

        Log.d("asdfqwer", supportFragmentManager.findFragmentById(R.id.main_fragment_container).toString())

        isPortrait = resources.configuration.orientation == ORIENTATION_PORTRAIT

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.action_settings -> {
            fragmentChanger(R.id.main_fragment_container, SettingsFragment())
            true
        }
        else -> super.onOptionsItemSelected(item)
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
                if (!isPortrait) {
                    val args: Bundle = bundleOf(
                        ARG_TITLE to "?", ARG_AUTHOR to "?", ARG_IMAGE to "0"
                    )
                    fragmentChanger(R.id.main_fragment_container_2, ArtDetailFragment(), args)
                }
                fragmentChanger(R.id.main_fragment_container, ArtworksViewFragment())
                binding.mainFragmentContainer2?.visibility = View.VISIBLE
                true
            }
            R.id.nav_settings ->{
                fragmentChanger(R.id.main_fragment_container, SettingsFragment())
                binding.mainFragmentContainer2?.visibility = View.INVISIBLE
                binding.mainFragmentContainer2?.isEnabled = false
                true
            }
            R.id.nav_logoff ->{
                LogoffDialogFragment().show(supportFragmentManager, "LOGOFF")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fragmentChanger(container: Int, fragment: Fragment, args: Bundle? = null) {
        supportFragmentManager.commit {
            if (args==null)
                replace(container, fragment)
            else
                replace<ArtDetailFragment>(container, args = args)
            addToBackStack(null)
            setReorderingAllowed(false)
        }
    }

    private fun setNavHeaderUserdata(view: View) {
        val user: TextView = view.findViewById(R.id.user_logged_nav)
        user.text = LoginFragment.user_logged?.username
    }

}