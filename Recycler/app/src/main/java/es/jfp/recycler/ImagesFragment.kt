package es.jfp.recycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import androidx.recyclerview.widget.GridLayoutManager
import es.jfp.recycler.adapters.ImageAdapter
import es.jfp.recycler.classes.Image
import es.jfp.recycler.databinding.FragmentImagesBinding


class ImagesFragment : Fragment() {

    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentImagesBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        return binding.root

    }

    private fun setUpRecyclerView() {
        val images = mutableListOf(
            Image(R.drawable.pikachu_ash),
            Image(R.drawable.pikachu_capi),
            Image(R.drawable.pikachu_real),
            Image(R.drawable.pikachu_cute),
            Image(R.drawable.pikachu_ash),
            Image(R.drawable.pikachu_capi),
            Image(R.drawable.pikachu_detective),
            Image(R.drawable.pikachu_cute)
        )

        val imagesAdapter = ImageAdapter(context!!, images)

        binding.recyclerImagesView.adapter = imagesAdapter
        binding.recyclerImagesView.layoutManager = GridLayoutManager(context!!, 3, )
    }

}