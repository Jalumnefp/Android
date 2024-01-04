package es.jfp.crud_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.jfp.crud_retrofit.adapters.CarsAdapter
import es.jfp.crud_retrofit.api.CarResponse
import es.jfp.crud_retrofit.api.CarsService
import es.jfp.crud_retrofit.api.RetrofitObject
import es.jfp.crud_retrofit.databinding.ActivityMainBinding
import es.jfp.crud_retrofit.enums.CarDialogMode
import es.jfp.crud_retrofit.fragments.dialogs.CarDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), CarDialog.CreateCarDialogButtons {

    lateinit var binding: ActivityMainBinding
    lateinit var carsAdapter: CarsAdapter

    private lateinit var carsList: MutableList<CarResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.mainActionBar)
        setUpRecyclerView(binding.mainRecyclerView)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_add -> {
                CarDialog(CarDialogMode.CREATE).show(this.supportFragmentManager, "CREATE_DIALOG")
                true
            }
            else -> false
        }
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView) {

        carsList = mutableListOf()

        getAllCurrentCars()

        val updateAction = { car_id: Int ->
            CarDialog(CarDialogMode.UPDATE, car_id).show(this.supportFragmentManager, "UPDATE_DIALOG")
        }

        val deleteAction = { car_id: Int ->
            CarDialog(CarDialogMode.DELETE, car_id).show(this.supportFragmentManager, "DELETE_DIALOG")
        }

        carsAdapter = CarsAdapter(carsList, updateAction, deleteAction)
        recyclerView.adapter = carsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

    }

    override fun onClickCreateButton(dialog: DialogFragment, car: CarResponse) {
        lifecycleScope.launch(Dispatchers.IO) {
            RetrofitObject.getInstance().create(CarsService::class.java).addCar(car)
            withContext(Dispatchers.Main) {
                getAllCurrentCars()
            }
        }
    }

    override fun onClickUpdateButton(dialog: DialogFragment, car: CarResponse, car_id: Int) {
        Log.d("UPD", car_id.toString())
        lifecycleScope.launch(Dispatchers.IO) {
            RetrofitObject.getInstance().create(CarsService::class.java).updateCar(car_id, car)
            withContext(Dispatchers.Main) {
                getAllCurrentCars()
            }
        }
    }

    override fun onClickDeleteButton(dialog: DialogFragment, car_id: Int) {
        Log.d("DEL", car_id.toString())
        lifecycleScope.launch(Dispatchers.IO) {
            RetrofitObject.getInstance().create(CarsService::class.java).deleteCar(car_id)
            withContext(Dispatchers.Main) {
                getAllCurrentCars()
            }
        }
    }

    private fun getAllCurrentCars() {
        lifecycleScope.launch(Dispatchers.IO) {
            val call = RetrofitObject.getInstance().create(CarsService::class.java).getAllCars()
            val response = call.body()
            withContext(Dispatchers.Main) {
                response?.let {
                    carsList.clear()
                    carsList.addAll(response)
                    carsAdapter.notifyDataSetChanged()
                }
            }
        }
    }

}