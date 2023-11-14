package es.jfp.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import es.jfp.bottomnavigation.databinding.ActivityMainBinding
import es.jfp.bottomnavigation.databinding.FragmentStarBinding

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.item_star -> {
            replaceFragment(StarFragment())
            true
        }
        R.id.item_toys -> {
            replaceFragment(ToysFragment())
            true
        }
        R.id.item_wine -> {
            Toast.makeText(this, "Wine is forbidden here", Toast.LENGTH_SHORT).show()
            true
        }
        else -> false
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainerView, fragment)
            //setReorderingAllowed(true)
            addToBackStack(null)
        }
    }


}