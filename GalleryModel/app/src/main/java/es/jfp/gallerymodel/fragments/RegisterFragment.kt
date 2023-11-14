package es.jfp.gallerymodel.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import es.jfp.gallerymodel.classes.User
import es.jfp.gallerymodel.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment(), OnClickListener {

    interface RegisterFragmentButtons {
        fun onClickRegisterButton()
    }

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private var miListener: RegisterFragmentButtons? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.registerButton.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {

        when (v) {
            binding.registerButton ->{
                if (passwordOk()) {
                    registerUser(User(
                        binding.registerUsernameEditText.text.toString(),
                        binding.registerPasswordEditText.text.toString()
                    ))
                    miListener?.onClickRegisterButton()
                } else {
                    Snackbar.make(binding.root, "Inicio de sesión incorrecto!", Snackbar.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun registerUser(user: User) {
        users_logged.add(user)

    }

    private fun passwordOk(): Boolean =
        binding.registerPasswordEditText.text.toString() == binding.registerPasswordReEditText.text.toString()

    companion object {
        val users_logged: MutableList<User> = mutableListOf()
    }


}