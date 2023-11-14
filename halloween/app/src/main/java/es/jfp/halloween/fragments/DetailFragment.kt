package es.jfp.halloween.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.jfp.halloween.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var name: String
    private var img: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(PARAM_NAME).toString()
            img = it.getInt(PARAM_IMG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.priateNameDetail.text = name
        binding.pirateImgDetail.setImageResource(img)

        return binding.root
    }

    companion object {
        const val PARAM_NAME: String = "pirate_name"
        const val PARAM_IMG: String = "pirate_img"
    }

}