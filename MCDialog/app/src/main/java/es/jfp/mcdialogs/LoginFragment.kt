package es.jfp.mcdialogs

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import es.jfp.mcdialogs.databinding.FragmentLoginBinding


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
        return username == "Santiago" && password == "asdf1234"
    }

    override fun onClick(v: View) {
        when(v) {
            binding.loginButton -> {
                if (logged) {
                    miListener?.onClickLoginButton()
                } else {
                    Snackbar.make(binding.root, "El único usuario posible es Santiago, cuya contraseña es asdf1234", Snackbar.LENGTH_LONG)
                        .setAction("REGISTRARSE AHORA") {
                            miListener?.onClickRegisterButton()
                        }
                        .show()
                }
            }
            binding.registerButton -> {
                if (testLogin()) {
                    miListener?.onClickRegisterButton()
                } else {
                    Snackbar.make(binding.root, "El único usuario posible es Santiago, cuya contraseña es asdf1234", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        var logged: Boolean = false
    }

}