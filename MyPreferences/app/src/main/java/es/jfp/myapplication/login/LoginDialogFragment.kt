package es.jfp.myapplication.login
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputLayout
import es.jfp.myapplication.R


class LoginDialogFragment : DialogFragment() {

    interface LoginDialogFragmentButtons {
        fun onClickOkButton()
    }


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

            val username = LoginFragment.user_to_register.username
            val password = LoginFragment.user_to_register.password

            builder
                .setTitle("This will be your user, ok?")
                .setMessage("Username: $username\nPassword: $password")
                .setPositiveButton("OK") { dialog, id ->
                    LoginFragment.register_user.add(LoginFragment.user_to_register)
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