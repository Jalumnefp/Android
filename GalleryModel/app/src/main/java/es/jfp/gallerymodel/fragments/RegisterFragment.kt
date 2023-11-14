package es.jfp.gallerymodel.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import es.jfp.gallerymodel.R
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
        miListener?.onClickRegisterButton()

    }


}