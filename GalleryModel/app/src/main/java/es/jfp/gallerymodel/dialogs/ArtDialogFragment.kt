package es.jfp.gallerymodel.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import es.jfp.gallerymodel.R
import es.jfp.gallerymodel.classes.Art
import es.jfp.gallerymodel.fragments.ArtworksViewFragment


class ArtDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater

            builder
                .setView(inflater.inflate(R.layout.fragment_art_dialog, null))
                .setPositiveButton("CREATE") {dialog, id ->
                    val title = activity!!.findViewById<View?>(R.id.toadd_title_art).toString()
                    val author = activity!!.findViewById<View?>(R.id.toadd_author_art).toString()
                    ArtworksViewFragment.artworks.add(
                        Art(0, title, author)
                    )

                }
                .setNegativeButton("CANCEL") {dialog, id ->

                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}