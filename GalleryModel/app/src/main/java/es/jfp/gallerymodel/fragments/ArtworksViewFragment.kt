package es.jfp.gallerymodel.fragments

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import es.jfp.gallerymodel.R
import es.jfp.gallerymodel.activitys.MainActivity
import es.jfp.gallerymodel.adaptets.ArtAdapter
import es.jfp.gallerymodel.classes.Art
import es.jfp.gallerymodel.databinding.FragmentArtworksViewBinding
import es.jfp.gallerymodel.dialogs.ArtDialogFragment
import es.jfp.gallerymodel.fragments.ArtDetailFragment.Companion.ARG_AUTHOR
import es.jfp.gallerymodel.fragments.ArtDetailFragment.Companion.ARG_IMAGE
import es.jfp.gallerymodel.fragments.ArtDetailFragment.Companion.ARG_TITLE


class ArtworksViewFragment : Fragment() {

    private var _binding: FragmentArtworksViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentArtworksViewBinding.inflate(inflater, container, false)

        binding.floatingButtonAdd.setOnClickListener {
            ArtDialogFragment().show(requireActivity().supportFragmentManager, "ADD_ART_DIALOG")
        }

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.supportActionBar?.title = this::class.java.simpleName
    }

    private fun setupRecyclerView() {

        val artAdapter = ArtAdapter(context!!, artworks) { art: Art ->
            val bundle: Bundle = bundleOf(
                ARG_TITLE to art.name,
                ARG_AUTHOR to art.author,
                ARG_IMAGE to art.image
            )
            if (resources.configuration.orientation == ORIENTATION_PORTRAIT) {
               fragmentChanger(R.id.main_fragment_container, bundle)
            } else {
                fragmentChanger(R.id.main_fragment_container_2, bundle)
            }

        }

        binding.artworkRecyclerContainer.adapter = artAdapter
        binding.artworkRecyclerContainer.setHasFixedSize(true)
        binding.artworkRecyclerContainer.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun fragmentChanger(container: Int, args: Bundle) {
        activity?.supportFragmentManager?.commit {
            replace<ArtDetailFragment>(container, args = args)
            addToBackStack(null)
            setReorderingAllowed(false)
        }
    }

    companion object {
        val artworks = mutableListOf(
            Art(R.drawable.gernika, "Guernica", "Pablo Picasso", null),
            Art(R.drawable.starnight, "Starry night", "Vincent Van Gogh", null),
            Art(R.drawable.grito, "The shout", "Edvard Munch", null),
            Art(R.drawable.sunflowers, "Sunflowers", "Vincent Van Gogh", null),
            Art(R.drawable.ic_launcher_foreground, "asdfa", "asdfasd", null),
            Art(R.drawable.ic_launcher_foreground, "Mona Lisa", "Leonardo da Vinci", null),
            Art(R.drawable.ic_launcher_foreground, "The Night Watch", "Rembrandt", null),
            Art(R.drawable.ic_launcher_foreground, "The Thinker", "Auguste Rodin", null),
            Art(R.drawable.ic_launcher_foreground, "The Last Supper", "Leonardo da Vinci", null),
            Art(R.drawable.ic_launcher_foreground, "The Scream", "Edvard Munch", null),
            Art(R.drawable.ic_launcher_foreground, "Star Birth", "NASA", null),
            Art(R.drawable.ic_launcher_foreground, "The Birthday", "Marc Chagall", null),
            Art(R.drawable.ic_launcher_foreground, "Water Lilies", "Claude Monet", null),
            Art(R.drawable.ic_launcher_foreground, "Guernica", "Pablo Picasso", null),
            Art(R.drawable.ic_launcher_foreground, "Virgin of the Rocks", "Leonardo da Vinci", null),
            Art(R.drawable.ic_launcher_foreground, "The Creation of Adam", "Michelangelo", null),
            Art(R.drawable.ic_launcher_foreground, "The Night Cafe", "Vincent Van Gogh", null),
            Art(R.drawable.ic_launcher_foreground, "Girl with a Pearl Earring", "Johannes Vermeer", null),
            Art(R.drawable.ic_launcher_foreground, "Balduccino", "Gian Lorenzo Bernini", null),
            Art(R.drawable.ic_launcher_foreground, "The Sistine Chapel Ceiling", "Michelangelo", null),
            Art(R.drawable.ic_launcher_foreground, "Olympia", "Édouard Manet", null)

            )
    }


}