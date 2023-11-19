package es.jfp.gallerymodel.activitys

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.snackbar.Snackbar
import es.jfp.gallerymodel.R
import es.jfp.gallerymodel.databinding.ActivityLoginBinding
import es.jfp.gallerymodel.fragments.LoginFragment
import es.jfp.gallerymodel.fragments.LoginFragment.LoginFragmentButtons
import es.jfp.gallerymodel.fragments.RegisterFragment
import es.jfp.gallerymodel.fragments.RegisterFragment.RegisterFragmentButtons

class LoginActivity : AppCompatActivity(), LoginFragmentButtons, RegisterFragmentButtons {

    private val READ_ECSTERNAL_STORAGE: Int = 16253

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.loginToolbar)

        requestPermissionIfNotAllowed()

    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == READ_ECSTERNAL_STORAGE) {
            val msg = if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                "Storage service granted!"
            } else {
                "Storage service denied!"
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

    override fun onClickSignupButton() {
        this.defaultFragmentChanger(RegisterFragment())
    }

    override fun onClickRegisterButton() {
        this.defaultFragmentChanger(LoginFragment())
    }

    private fun defaultFragmentChanger(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.login_fragment_container, fragment)
            addToBackStack(null)
            setReorderingAllowed(false)
        }
    }

    private fun requestPermissionIfNotAllowed() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_ECSTERNAL_STORAGE
            )
        }
    }

}