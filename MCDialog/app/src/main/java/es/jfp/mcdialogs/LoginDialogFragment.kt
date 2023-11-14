package es.jfp.mcdialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class LoginDialogFragment : DialogFragment() {

    interface LoginDialogFragmentButtons {
        fun onClickOkButton()
    }

    private lateinit var username: String
    private lateinit var password: String

    private var miListener: LoginDialogFragmentButtons? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        miListener = context as? LoginDialogFragmentButtons
        if (miListener == null) {
            throw java.lang.NullPointerException("$context must implement LoginDialogFragmentButtons")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            username = "Santiago"
            password = "asdf1234"

            builder
                .setTitle("This will be your user, ok?")
                .setMessage("Username: $username\nPassword: $password")
                .setPositiveButton("OK") { dialog, id ->
                    miListener?.onClickOkButton()
                }

            builder.create()
        } ?: throw java.lang.IllegalStateException("Activity cannot be null")
    }

    override fun onDetach() {
        super.onDetach()
        miListener = null
    }

}