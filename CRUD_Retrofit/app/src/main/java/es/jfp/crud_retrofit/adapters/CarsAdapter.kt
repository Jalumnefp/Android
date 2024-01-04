package es.jfp.crud_retrofit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.jfp.crud_retrofit.api.CarResponse
import es.jfp.crud_retrofit.databinding.CarRecyclerItemBinding

class CarsAdapter(
    private val cars: MutableList<CarResponse>,
    private val updateListener: (Int) -> Unit,
    private val deleteListener: (Int) -> Unit
): RecyclerView.Adapter<CarsAdapter.CarsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val binding = CarRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarsViewHolder(binding)
    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        val item = cars[position]
        holder.bindItem(item)

        holder.itemView.setOnClickListener{ updateListener(item.id) }
        holder.itemView.setOnLongClickListener{ deleteListener(item.id); true }

    }

    class CarsViewHolder(binding: CarRecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val carBrand: TextView = binding.textBrand
        private val carModel: TextView = binding.textModel
        private val carPhoto: ImageView = binding.imagePhoto

        fun bindItem(car: CarResponse) {
            carBrand.text = car.brand
            carModel.text = car.model
            Picasso.get().load(car.photo).into(carPhoto)
        }

    }

}