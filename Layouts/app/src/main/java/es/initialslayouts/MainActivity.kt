package es.initialslayouts

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import es.initialslayouts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MenuFragment.MenuNavButtons, LoginFragment.LoginButtons, RegistredUserFragment.LogoffButtons {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onLoginButtonClick() {
        supportFragmentManager.commit {
            replace<LoginFragment>(R.id.menuFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onCalculatorButtonClick() {
        supportFragmentManager.commit {
            replace<CalculatorFragment>(R.id.menuFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onLoginClickButton() {
        val username: String = this.getEditTextContent(R.id.editTextTextPersonName)
        val password: String = this.getEditTextContent(R.id.editTextTextPassword)

        if (LoginFragment.certificateLogin(password)) {
            val bundle: Bundle = bundleOf(
                RegistredUserFragment.USERNAME_KEY to username,
                RegistredUserFragment.PASSWORD_KEY to password)
            supportFragmentManager.commit {
                replace<RegistredUserFragment>(R.id.menuFragmentContainer, args = bundle)
                setReorderingAllowed(true)
                addToBackStack(null)}
        } else {
            Toast.makeText(this, "ERROR. The password must be 1234", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getEditTextContent(editText: Int): String {
        return findViewById<EditText?>(editText).text.toString()
    }

    override fun onClickLogoffButton() {
        onBackPressedDispatcher.onBackPressed()
        Toast.makeText(this, "La sesión se ha cerrado correctamente!", Toast.LENGTH_SHORT).show()
    }

}