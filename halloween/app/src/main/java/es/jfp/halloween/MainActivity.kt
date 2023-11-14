package es.jfp.halloween

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import es.jfp.halloween.databinding.ActivityMainBinding
import es.jfp.halloween.fragments.*

class MainActivity : AppCompatActivity(), FirstFragment.FirstFragmentButtons {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onClickEnterButton() {
       changeMainFragmentContainer(MonsterFragment())
    }

    private fun changeMainFragmentContainer(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container_main, fragment)
            addToBackStack(null)
            setReorderingAllowed(false)
        }
    }

}