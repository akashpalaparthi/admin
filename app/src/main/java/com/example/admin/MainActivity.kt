package com.example.admin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var kioskModeRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kioskModeRef = FirebaseDatabase.getInstance().getReference("kiosk_mode")

        val toggleButton: Button = findViewById(R.id.toggleKioskButton)

        toggleButton.setOnClickListener {
            toggleKioskMode()
        }
    }

    private fun toggleKioskMode() {
        kioskModeRef.get().addOnSuccessListener { snapshot ->
            val currentState = snapshot.getValue(Boolean::class.java) ?: false
            kioskModeRef.setValue(!currentState)
        }.addOnFailureListener {
            Log.e("AdminApp", "Failed to get current state of kiosk mode.")
        }
    }
}
