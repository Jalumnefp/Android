package es.initialslayouts

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import es.initialslayouts.databinding.FragmentLoginBinding


class LoginFragment : Fragment(), OnClickListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var miListener: LoginButtons? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        miListener = context as? LoginButtons
        if(miListener == null) {
            throw ClassCastException("$context must implement LoginButtons")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.editTextTextPassword.text.toString()

        binding.loginBtn.setOnClickListener(this)

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        miListener = null
    }

    interface LoginButtons {
        fun onLoginClickButton()
    }

    override fun onClick(v: View) {
        when(v) {
            binding.loginBtn -> miListener?.onLoginClickButton()
        }
    }

    companion object {
        fun certificateLogin(password: String): Boolean {
            return password == "1234"
        }
    }

}