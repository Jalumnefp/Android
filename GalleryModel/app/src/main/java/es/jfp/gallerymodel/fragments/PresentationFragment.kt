package es.jfp.gallerymodel.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.jfp.gallerymodel.R
import es.jfp.gallerymodel.activitys.MainActivity
import es.jfp.gallerymodel.databinding.FragmentPresentationBinding
import java.util.*


class PresentationFragment : Fragment() {

    private var _binding: FragmentPresentationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPresentationBinding.inflate(inflater, container, false)
        Log.d("asdf", Locale.getDefault().toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.supportActionBar?.title = this::class.java.simpleName
    }


}