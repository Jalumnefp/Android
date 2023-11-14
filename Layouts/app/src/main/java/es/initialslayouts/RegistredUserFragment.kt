package es.initialslayouts

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import es.initialslayouts.databinding.FragmentRegistredUserBinding


class RegistredUserFragment : Fragment(), OnClickListener {

    private var _binding: FragmentRegistredUserBinding? = null
    private val binding get() = _binding!!

    private var miListener: LogoffButtons? = null

    private lateinit var username: String
    private lateinit var password: String

    override fun onAttach(context: Context) {
        super.onAttach(context)

        miListener = context as? LogoffButtons
        if (miListener == null) {
            throw ClassCastException("$context must implement LogofButtons")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            username = it.getString(USERNAME_KEY).toString()
            password = it.getString(PASSWORD_KEY).toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegistredUserBinding.inflate(inflater, container, false)

        binding.logofBtn.setOnClickListener(this)

        binding.textViewUserDone.text = username
        binding.textViewPasswordDone.text = "*************"
        binding.checkBox.setOnClickListener(this)

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        miListener = null
    }

    interface LogoffButtons {
        fun onClickLogoffButton()
    }

    override fun onClick(v: View) {
        when(v) {
            binding.logofBtn -> miListener?.onClickLogoffButton()
            binding.checkBox -> this.toggleShowPassword()
        }
    }

    fun toggleShowPassword(){
        if (binding.checkBox.isChecked) {
            binding.textViewPasswordDone.text = password
        } else {
            binding.textViewPasswordDone.text = "*************"
        }
    }

    companion object {
        const val USERNAME_KEY: String = "username_key"
        const val PASSWORD_KEY: String = "password_key"
    }

}