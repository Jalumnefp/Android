package es.jfp.gallerymodel.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import es.jfp.gallerymodel.activitys.LoginActivity
import es.jfp.gallerymodel.classes.User
import es.jfp.gallerymodel.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment(), OnClickListener {

    interface RegisterFragmentButtons {
        fun onClickRegisterButton()
    }

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private var miListener: RegisterFragmentButtons? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        miListener = context as? RegisterFragmentButtons
        if (miListener == null) {
            throw java.lang.NullPointerException("$context must implement RegisterFragmentButtons")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.registerButton.setOnClickListener(this)
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

        when (v) {
            binding.registerButton ->{
                val user: String = binding.registerUsernameEditText.text.toString()
                val passwd1: String = binding.registerPasswordEditText.text.toString()
                val passwd2: String = binding.registerPasswordReEditText.text.toString()
                val logged: Boolean = registerUser(user, passwd1, passwd2)
                if (logged) {
                    miListener?.onClickRegisterButton()
                }

            }

        }
    }

    private fun registerUser(user: String, passwd1: String, passwd2: String): Boolean {
        val msg: String = if (anyFieldEmpty(user, passwd1, passwd2)) {
            if (passwordOk(passwd1, passwd2)) {
                users_logged.add(
                    User(user, passwd1)
                )
                return true
            } else {
                "Las contraseñas deben coincidir"
            }
        } else {
            "No pueden haber campos vacios!"
        }
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
        return false

    }

    private fun passwordOk(passwd1: String, passwd2: String): Boolean = passwd1 == passwd2

    private fun anyFieldEmpty(username: String, passwd1: String, passwd2: String): Boolean =
        username.isNotEmpty() and passwd1.isNotEmpty() and  passwd2.isNotEmpty()

    companion object {
        val users_logged: MutableList<User> = mutableListOf()
    }


}