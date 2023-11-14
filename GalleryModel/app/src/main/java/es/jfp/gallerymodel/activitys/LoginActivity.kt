package es.jfp.gallerymodel.activitys

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.commit
import com.google.android.material.snackbar.Snackbar
import es.jfp.gallerymodel.R
import es.jfp.gallerymodel.databinding.ActivityLoginBinding
import es.jfp.gallerymodel.fragments.LoginFragment.LoginFragmentButtons
import es.jfp.gallerymodel.fragments.RegisterFragment

class LoginActivity : AppCompatActivity(), LoginFragmentButtons {

    private val BLUETOOTH_SERVICE_CODE: Int = 16253

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == BLUETOOTH_SERVICE_CODE) {
            val msg = if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                "Bluetooth service granted!"
            } else {
                "Bluetooth service denied!"
            }
            Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onClickLoginButton() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onClickSingupButton() {
        supportFragmentManager.commit {
            replace(R.id.login_fragment_container, RegisterFragment())
            addToBackStack(null)
            setReorderingAllowed(false)
        }
    }

}