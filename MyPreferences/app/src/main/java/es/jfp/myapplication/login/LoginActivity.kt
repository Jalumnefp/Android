package es.jfp.myapplication.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import es.jfp.myapplication.login.LoginFragment.LoginFragmentButtons
import es.jfp.myapplication.databinding.ActivityLoginBinding
import es.jfp.myapplication.navigation.MainActivity


class LoginActivity : AppCompatActivity(), LoginFragmentButtons,
    LoginDialogFragment.LoginDialogFragmentButtons {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onClickRegisterButton() {
        LoginDialogFragment().show(supportFragmentManager, "LOGIN_INFO")
    }

    override fun onClickLoginButton() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onClickOkButton() {
        Snackbar.make(binding.root, "Usuario registrado correctamente", Snackbar.LENGTH_SHORT).show()
    }
}