package es.jfp.halloween.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import es.jfp.halloween.MainActivity
import es.jfp.halloween.R
import es.jfp.halloween.adapter.PiratesAdapter
import es.jfp.halloween.classes.Pirate
import es.jfp.halloween.databinding.FragmentMonsterBinding
import es.jfp.halloween.fragments.DetailFragment.Companion.PARAM_IMG
import es.jfp.halloween.fragments.DetailFragment.Companion.PARAM_NAME


class MonsterFragment : Fragment() {

    private var _binding: FragmentMonsterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMonsterBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView() {

        val pirates = mutableListOf(
            Pirate("Monkey D. Luffy", R.drawable.luffy),
            Pirate("Barbanegra", R.drawable.barbanegra),
            Pirate("Jack Sparrow", R.drawable.jack),
            Pirate("Kraken", R.drawable.kraken),
            Pirate("Bob", R.drawable.bob),
            Pirate("Holandés Errante", R.drawable.holandes),
            Pirate("Gold D. Roger", R.drawable.gold)
        )


        val piratesAdapter = PiratesAdapter(context!!, pirates) { pirate: Pirate ->

            activity?.supportFragmentManager?.commit {
                val bundle: Bundle = bundleOf(
                    PARAM_NAME to pirate.name,
                    PARAM_IMG to pirate.image
                )
                replace<DetailFragment>(R.id.fragment_container_main, args = bundle)
                addToBackStack(null)
                setReorderingAllowed(false)
            }


        }

        binding.recyclerContainer.adapter = piratesAdapter
        binding.recyclerContainer.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)

    }


}