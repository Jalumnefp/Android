package es.jfp.gallerymodel.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import es.jfp.gallerymodel.R
import es.jfp.gallerymodel.activitys.LoginActivity
import es.jfp.gallerymodel.classes.Art
import es.jfp.gallerymodel.fragments.ArtworksViewFragment
import java.util.*


class LogoffDialogFragment : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder
                .setTitle(R.string.close_session)
                .setMessage(R.string.logof_msg)
                .setPositiveButton(R.string.close_session) { dialog, id ->
                    val intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton(R.string.cancel) { dialog, id -> }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }
}