package es.jfp.recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import es.jfp.recycler.R
import es.jfp.recycler.classes.Image

class ImageAdapter(private val context: Context, private val images: MutableList<Image>): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val img = images[position]
        holder.bindItem(img)
    }

    class ImageViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val img: ImageView = view.findViewById(R.id.imageView)

        fun bindItem(image: Image) {
            img.setImageResource(image.image)
        }
    }

}