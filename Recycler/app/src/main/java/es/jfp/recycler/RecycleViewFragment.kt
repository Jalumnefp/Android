package es.jfp.recycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import es.jfp.recycler.adapters.ContactAdapter
import es.jfp.recycler.classes.Contact
import es.jfp.recycler.databinding.FragmentRecycleViewBinding


class RecycleViewFragment : Fragment() {

    private var _binding: FragmentRecycleViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRecycleViewBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView() {
        val contacts = mutableListOf<Contact>(
            Contact("Jose Luís", "Valencia"),
            Contact("Jose", "Albacete"),
            Contact("Luís", "Valencia"),
            Contact("Jose María", "Valencia"),
            Contact("María", "Mallorca"),
            Contact("María José", "Valencia"),
            Contact("María Luisa", "Toledo"),
            Contact("Luisa", "Valencia"),
        )

        val contactsAdapter = ContactAdapter(context!!, contacts)

        binding.recyclerView.adapter = contactsAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)

    }


}