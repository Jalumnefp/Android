package es.jfp.bottomnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.jfp.bottomnavigation.databinding.FragmentToysBinding


class ToysFragment : Fragment() {

    private var _binding: FragmentToysBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentToysBinding.inflate(inflater, container, false)
        return binding.root
    }

}