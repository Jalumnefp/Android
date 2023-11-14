package es.jfp.halloween.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.jfp.halloween.R
import es.jfp.halloween.classes.Pirate

class PiratesAdapter(
    private val context: Context,
    private val pirates: MutableList<Pirate>,
    private val miListener: (Pirate) -> Unit
    ) : RecyclerView.Adapter<PiratesAdapter.PiratesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PiratesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pirates_item, parent, false)
        return PiratesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pirates.size
    }

    override fun onBindViewHolder(holder: PiratesViewHolder, position: Int) {
        val item = pirates[position]
        holder.bindItem(item, context)
        holder.itemView.setOnClickListener { miListener(item)}
    }

    class PiratesViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val pirateName: TextView = view.findViewById(R.id.pirateName)
        private val pirateImg: ImageView = view.findViewById(R.id.pirateImg)

        fun bindItem(pirate: Pirate, context: Context) {
            pirateName.text = pirate.name
            pirateImg.setImageResource(pirate.image)

        }

    }

}