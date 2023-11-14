package es.initialslayouts

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import es.initialslayouts.databinding.FragmentMenuBinding


class MenuFragment : Fragment(), OnClickListener {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private var miListener: MenuNavButtons? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        miListener = context as? MenuNavButtons
        if (miListener == null) {
            throw java.lang.ClassCastException("$context must implement MenuNavButtons!")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.toLoginFragmentBtn.setOnClickListener(this)
        binding.toCalculatorFragmentBtn.setOnClickListener(this)

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        miListener = null
    }

    override fun onClick(v: View) {
        when(v) {
            binding.toLoginFragmentBtn -> miListener?.onLoginButtonClick()
            binding.toCalculatorFragmentBtn -> miListener?.onCalculatorButtonClick()
        }
    }

    interface MenuNavButtons {
        fun onLoginButtonClick()
        fun onCalculatorButtonClick()
    }

}