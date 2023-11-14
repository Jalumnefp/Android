package es.jfp.halloween.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.jfp.halloween.databinding.FragmentFirstBinding


class FirstFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var miListener: FirstFragmentButtons? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        miListener = context as? FirstFragmentButtons
        if (miListener==null) {
            throw java.lang.ClassCastException("$context must implement FirstFragmentButtons")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.enterBtn.setOnClickListener(this)

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        miListener = null
    }

    override fun onClick(v: View) {
        when(v) {
            binding.enterBtn -> miListener?.onClickEnterButton()
        }
    }

    interface FirstFragmentButtons {
        fun onClickEnterButton()
    }

}