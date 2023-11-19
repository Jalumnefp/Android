package es.jfp.gallerymodel.fragments

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import es.jfp.gallerymodel.activitys.LoginActivity
import es.jfp.gallerymodel.classes.User
import es.jfp.gallerymodel.databinding.FragmentLoginBinding


class LoginFragment : Fragment(), OnClickListener {

    interface LoginFragmentButtons {
        fun onClickLoginButton()
        fun onClickSignupButton()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? LoginActivity)?.supportActionBar?.title = this::class.java.simpleName
    }

    override fun onDetach() {
        super.onDetach()
        miListener = null
    }

    override fun onClick(v: View) {
        when(v) {
            binding.loginButton -> {

                val user = User(
                    binding.usernameEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )

                if (login(user)) {
                    miListener?.onClickLoginButton()
                    user_logged = user
                } else {
                    Snackbar.make(binding.root, "Inicio de sesión incorrecto", Snackbar.LENGTH_SHORT).show()
                }
            }
            binding.singupTextview -> miListener?.onClickSignupButton()
        }
    }

    private fun login(user: User): Boolean = RegisterFragment.users_logged.contains(user)

    companion object {
        var user_logged: User? = null
    }

}