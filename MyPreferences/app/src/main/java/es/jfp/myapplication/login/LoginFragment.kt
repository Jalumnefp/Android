package es.jfp.myapplication.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import es.jfp.myapplication.databinding.FragmentLoginBinding


class LoginFragment : Fragment(), OnClickListener {

    interface LoginFragmentButtons {
        fun onClickLoginButton()
        fun onClickRegisterButton()
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var miListener: LoginFragmentButtons? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        miListener = context as? LoginFragmentButtons
        if (miListener == null) {
            throw Exception("$context must implement LoginFragmentButtons")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginButton.setOnClickListener(this)
        binding.registerButton.setOnClickListener(this)

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        miListener = null
    }

    private fun testLogin(): Boolean {
        val username: String = binding.usernameTextInputEditText.text.toString()
        val password: String = binding.passwordTextInputEditText.text.toString()
        return register_user.contains(User(username, password))
    }

    private fun fieldsNotEmpty(): Boolean {
        val username: String = binding.usernameTextInputEditText.text.toString()
        val password: String = binding.passwordTextInputEditText.text.toString()
        return username.isNotEmpty() and password.isNotEmpty()
    }

    override fun onClick(v: View) {
        when(v) {
            binding.loginButton -> {
                if (fieldsNotEmpty()) {
                    if (testLogin()) {
                        val prefs = activity?.getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE)
                        if (prefs != null) {
                            with (prefs.edit()) {
                                putString("USERNAME_LOGGED", binding.usernameTextInputEditText.text.toString())
                                apply()
                            }
                        }
                        miListener?.onClickLoginButton()
                    } else {
                        Snackbar.make(binding.root, "Este usuario no se ha registrado", Snackbar.LENGTH_LONG)
                            .setAction("REGISTRARSE AHORA") {
                                user_to_register.username = binding.usernameTextInputEditText.text.toString()
                                user_to_register.password = binding.passwordTextInputEditText.text.toString()
                                miListener?.onClickRegisterButton()
                            }
                            .show()
                    }
                } else {
                    Snackbar.make(binding.root, "No pueden haber campos vacios!", Snackbar.LENGTH_LONG).show()
                }

            }
            binding.registerButton -> {
                if (fieldsNotEmpty()) {
                    if (!testLogin()) {
                        user_to_register.username = binding.usernameTextInputEditText.text.toString()
                        user_to_register.password = binding.passwordTextInputEditText.text.toString()
                        miListener?.onClickRegisterButton()
                    } else {
                        Snackbar.make(binding.root, "Este usuario ya ha sido registrado!", Snackbar.LENGTH_LONG).show()
                    }
                } else {
                    Snackbar.make(binding.root, "No pueden haber campos vacios!", Snackbar.LENGTH_LONG).show()
                }

            }
        }
    }

    companion object {
        val register_user: MutableList<User> = mutableListOf()
        val user_to_register = User("null", "null")
        
    }

}