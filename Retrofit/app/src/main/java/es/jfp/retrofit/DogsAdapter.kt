package es.jfp.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.jfp.retrofit.databinding.DogsViewBinding

class DogsAdapter (private val context: Context, private val dogImages: MutableList<String>): RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val binding = DogsViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return DogsViewHolder(binding)
    }

    override fun getItemCount(): Int = dogImages.size

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val item = dogImages[position]
        holder.bind(item)
    }

    class DogsViewHolder(binding: DogsViewBinding): RecyclerView.ViewHolder(binding.root) {

        private val dogPhoto: ImageView = binding.imageView

        fun bind(image: String) {
            Picasso.get().load(image).into(dogPhoto)
        }

    }


}