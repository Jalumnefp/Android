package com.example.permissions

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val CONTACTS_REQUEST_CODE = 1234

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                CONTACTS_REQUEST_CODE
            )
        } else {
            Toast.makeText(this, "Permission already granted!", Toast.LENGTH_SHORT).show()
        }

        val contactsBtn: Button = findViewById(R.id.button_contacts)
        contactsBtn.setOnClickListener(this)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == CONTACTS_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Contact permission granted!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Contact permission denied!", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.button_contacts -> {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Abriendo contactos", Toast.LENGTH_SHORT).show()
                    setContentView(R.layout.activity_permissions_result)
                } else {
                    Toast.makeText(this, "Esta funcion requiere permiso para leer contactos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}