package es.jfp.extratranslationanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.jfp.extratranslationanimation.databinding.ActivitySecondaryBinding

class SecondaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}