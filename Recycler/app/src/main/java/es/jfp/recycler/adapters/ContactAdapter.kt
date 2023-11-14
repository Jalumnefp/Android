package es.jfp.recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.jfp.recycler.R
import es.jfp.recycler.classes.Contact

class ContactAdapter(private val context: Context, private val contacts: MutableList<Contact>): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bindItem(contact)
    }

    class ContactViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val contactName: TextView = view.findViewById(R.id.contactName)
        private val contactCity: TextView = view.findViewById(R.id.contactCity)

        fun bindItem(contact: Contact) {
            contactName.text = contact.name
            contactCity.text = contact.city
        }



    }

}