package es.jfp.gallerymodel.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import es.jfp.gallerymodel.R
import es.jfp.gallerymodel.databinding.FragmentLoginBinding


class LoginFragment : Fragment(), OnClickListener {

    interface LoginFragmentButtons {
        fun onClickLoginButton()
        fun onClickSingupButton()
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var miListener: LoginFragmentButtons? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        miListener = context as? LoginFragmentButtons
        if (miListener==null) {
            throw java.lang.NullPointerException("$context must implement LoginFragmentButtons!")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginButton.setOnClickListener(this)
        binding.singupTextview.setOnClickListener(this)

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        miListener = null
    }

    override fun onClick(v: View) {
        when(v) {
            binding.loginButton -> {
                if (login()) {
                    miListener?.onClickLoginButton()
                } else {
                    Snackbar.make(binding.root, "Inicio de sesión incorrecto", Snackbar.LENGTH_SHORT).show()
                }
            }
            binding.singupTextview -> miListener?.onClickSingupButton()
        }
    }

    private fun login(): Boolean {
        val username: String = binding.usernameEditText.text.toString()
        val password: String = binding.passwordEditText.text.toString()
        return username.isEmpty() and password.isEmpty()
    }

}