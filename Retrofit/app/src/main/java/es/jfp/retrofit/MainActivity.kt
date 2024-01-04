package es.jfp.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import es.jfp.retrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var dogslist: MutableList<String> = mutableListOf()
    private lateinit var adapter: DogsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        binding.searchButton.setOnClickListener {
            val breed: String = binding.breedEditText.text.toString()
            searchDogBread(breed)
        }

    }

    private fun setUpRecyclerView() {

        adapter = DogsAdapter(this, dogslist)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun searchDogBread(breed: String) {
        if (!breed.isNullOrEmpty()) {
            lifecycleScope.launch(Dispatchers.IO) {
                val call = RetrofitObject.getInstance()
                    .create(DogsService::class.java).getDogs(breed.lowercase())
                val response = call.body()
                withContext(Dispatchers.Main) {
                    if (response?.status == "success") {
                        dogslist.clear()
                        dogslist.addAll(response.images)
                        adapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@MainActivity, "Error getting the dogs", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Toast.makeText(this@MainActivity, "You must type a breed!", Toast.LENGTH_SHORT).show()

        }
        Log.d("TESTINGACIÓN", dogslist.size.toString())
    }


}