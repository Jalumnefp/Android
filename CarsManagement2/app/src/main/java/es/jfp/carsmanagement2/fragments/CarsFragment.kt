package es.jfp.carsmanagement2.fragments

import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.jfp.carsmanagement2.R
import es.jfp.carsmanagement2.adapters.CarsAdapter
import es.jfp.carsmanagement2.database.AppDB
import es.jfp.carsmanagement2.database.Car
import es.jfp.carsmanagement2.databinding.FragmentCarsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class CarsFragment : Fragment(){

    private lateinit var binding: FragmentCarsBinding
    private var cars = mutableListOf<Car>()
    private lateinit var carsAdapter: CarsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding  = FragmentCarsBinding.inflate(inflater, container, false)
        binding.btnInsert.setOnClickListener {
            if(!binding.etBrand.text.isNullOrBlank() && !binding.etModel.text.isNullOrBlank()){
                insertCoroutine()
            }else{
                Toast.makeText(requireContext(), getString(R.string.warn_car_empty), Toast.LENGTH_SHORT).show()
            }
        }
        setRecyclerView()
        return binding.root
    }

    private fun setRecyclerView(){
        getAllCoroutine()
        setRecyclerAdapter()
    }

    private fun cleanCar(){
        carsAdapter.notifyDataSetChanged()
        binding.etBrand.setText("")
        binding.etModel.setText("")
    }

    private fun insertCoroutine() {
        lifecycleScope.launch(Dispatchers.IO) {
            val newcar = Car(
                brand = binding.etBrand.text.toString(),
                model = binding.etModel.text.toString()
            )
            val id = AppDB.getInstance(requireContext()).carDao().insert(newcar).toInt()
            newcar.id = id
            withContext(Dispatchers.Main) {
                cars.add(newcar)
                cleanCar()
            }
        }
    }

    private fun getAllCoroutine() {
        var databaseCars: List<Car>? = null
        lifecycleScope.launch(Dispatchers.IO) {
            databaseCars = AppDB.getInstance(requireContext()).carDao().getAll()
            withContext(Dispatchers.Main) {
                databaseCars?.let {
                    cars.addAll(it)
                }
                cleanCar()
            }
        }
    }

    private fun removeCoroutine(car: Car) {
        lifecycleScope.launch(Dispatchers.IO) {
            AppDB.getInstance(requireContext()).carDao().delete(car)
            withContext(Dispatchers.Main) {
                cars.remove(car)
                cleanCar()
            }
        }
    }

    private fun setRecyclerAdapter() {
        val carClickFunction = {car: Car ->
            if (cars.contains(car)) {
                removeCoroutine(car)
            }
        }
        carsAdapter = CarsAdapter(requireContext(), cars, carClickFunction)
        binding.rvCars.adapter = carsAdapter
        binding.rvCars.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
    }
}