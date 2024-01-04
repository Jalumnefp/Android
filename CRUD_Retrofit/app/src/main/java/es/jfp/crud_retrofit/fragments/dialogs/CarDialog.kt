package es.jfp.crud_retrofit.fragments.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import es.jfp.crud_retrofit.api.CarResponse
import es.jfp.crud_retrofit.databinding.CarDialogBinding
import es.jfp.crud_retrofit.enums.CarDialogMode


class CarDialog(
    private var mode: CarDialogMode,
    private var car_id: Int? = -1
    ) : DialogFragment() {

    private lateinit var miListener: CreateCarDialogButtons
    private lateinit var binding: CarDialogBinding

    interface CreateCarDialogButtons {
        fun onClickCreateButton(dialog: DialogFragment, car: CarResponse)
        fun onClickUpdateButton(dialog: DialogFragment, car: CarResponse, car_id: Int)
        fun onClickDeleteButton(dialog: DialogFragment, car_id: Int)
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is CreateCarDialogButtons) {
            miListener = context
        } else {
            throw Exception("Your fragment must implement the interface CreateCarDialogButtons")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            binding = CarDialogBinding.inflate(requireActivity().layoutInflater)

            binding.carDialogTitle.text = "$mode CAR DIALOG"

            if (mode != CarDialogMode.DELETE) {
                builder.setView(binding.root)
            } else {
                builder.setTitle("DELETE CAR DIALOG")
                builder.setMessage("Do you want to delete this car with id $car_id?")
            }

            builder
                .setPositiveButton(mode.toString()) { dialog, id ->
                    val brand: String = binding.createBrandEditText.text.toString()
                    val model: String = binding.createModelEditText.text.toString()
                    val photo: String = binding.createPhotoEditText.text.toString()
                    val car = CarResponse(car_id!!, brand, model, photo)
                    when(mode) {
                        CarDialogMode.CREATE -> miListener.onClickCreateButton(this, car)
                        CarDialogMode.UPDATE -> miListener.onClickUpdateButton(this, car, car_id!!)
                        CarDialogMode.DELETE -> miListener.onClickDeleteButton(this, car_id!!)
                    }
                }
                .setNegativeButton("Cancell") {dialog, id ->

                }

            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

}