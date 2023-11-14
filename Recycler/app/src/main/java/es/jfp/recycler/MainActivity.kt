package es.jfp.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import es.jfp.recycler.databinding.ActivityMainBinding
import es.jfp.recycler.databinding.FragmentMenuBinding

class MainActivity : AppCompatActivity(), MenuFragment.MenuButtons {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onClickImagesButton() {
        replaceFragment(ImagesFragment())
    }

    override fun onClickListButton() {
        replaceFragment(RecycleViewFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainerView, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}