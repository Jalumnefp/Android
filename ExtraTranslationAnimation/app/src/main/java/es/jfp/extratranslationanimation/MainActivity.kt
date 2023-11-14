package es.jfp.extratranslationanimation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import es.jfp.extratranslationanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAnim.setOnClickListener(this)
        binding.buttonTransl.setOnClickListener(this)

    }

    private fun animation() {
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.my_animation)
        binding.buttonAnim.startAnimation(animation)
    }

    private fun translation() {
        val intent = Intent(this, SecondaryActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
    }

    override fun onClick(v: View) {
        when (v) {
            binding.buttonAnim -> animation()
            binding.buttonTransl -> translation()
        }
    }
}