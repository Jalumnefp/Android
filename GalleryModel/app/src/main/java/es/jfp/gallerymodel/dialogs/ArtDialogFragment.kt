package es.jfp.gallerymodel.dialogs

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso
import es.jfp.gallerymodel.R
import es.jfp.gallerymodel.classes.Art
import es.jfp.gallerymodel.fragments.ArtworksViewFragment
import org.w3c.dom.Text


class ArtDialogFragment : DialogFragment() {

    private var selectedImage: Uri? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.fragment_art_dialog, null)

            val authorTextview = view.findViewById<EditText>(R.id.toadd_author_art)
            val titleTextview = view.findViewById<EditText>(R.id.toadd_title_art)
            val btnImg = view.findViewById<Button>(R.id.add_art_btn)

            btnImg.setOnClickListener {
                openImagePicker()
            }

            builder
                .setView(view)
                .setPositiveButton(R.string.create) {dialog, id ->
                    onClickCreateButton(titleTextview, authorTextview)
                }
                .setNegativeButton(R.string.cancel) {dialog, id ->}

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // 
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1234 && resultCode == RESULT_OK && data != null) {

            selectedImage = data.data

        }
    }

    private fun onClickCreateButton(titleView: EditText, authorView: EditText) {
        val title = titleView.text.toString()
        val author = authorView.text.toString()
        ArtworksViewFragment.artworks.add(
            Art(0, title, author, selectedImage)
        )
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 1234)
    }

}