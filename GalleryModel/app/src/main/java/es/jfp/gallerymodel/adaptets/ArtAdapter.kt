package es.jfp.gallerymodel.adaptets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import es.jfp.gallerymodel.R
import es.jfp.gallerymodel.classes.Art
import es.jfp.gallerymodel.databinding.RecyclerArtItemBinding
import java.util.Objects

class ArtAdapter(
    private val context: Context,
    private val artworks: MutableList<Art>,
    private val miListener: (Art) -> Unit
): RecyclerView.Adapter<ArtAdapter.ArtViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val binding = RecyclerArtItemBinding.inflate(LayoutInflater.from(context), parent, false)

        val color = getRandomColor()
        val card: CardView = binding.recyclerArtCard
        card.setCardBackgroundColor(ContextCompat.getColor(context, color))

        return ArtViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return artworks.size
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val item = artworks[position]
        holder.bindItem(item, context)
        holder.itemView.setOnClickListener { miListener(item) }
    }

    private fun getRandomColor(): Int = when((Math.random()*5+1).toInt()) {
        1 -> R.color.card_background_1
        2 -> R.color.card_background_2
        3 -> R.color.card_background_3
        4 -> R.color.card_background_4
        5 -> R.color.card_background_5
        else -> R.color.white
    }

    class ArtViewHolder(binding: RecyclerArtItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val artImage: ImageView = binding.artImageview
        private val artName: TextView = binding.artTitleTextview
        private val artAuthor: TextView = binding.artAuthorTextview

        fun bindItem(art: Art, context: Context) {
            artImage.setImageResource(art.image)
            artName.text = art.name
            artAuthor.text = art.author
        }

    }

}