package com.example.consumervalorantagent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val uuidValorantEditText = findViewById<EditText>(R.id.editTextId)
        val searchButton = findViewById<Button>(R.id.buttonFetchValorant)

        searchButton.setOnClickListener {
            val uuid = uuidValorantEditText.text.toString().trim()

            if (uuid.isNotEmpty()) {
                val intent = Intent(this, ResultadoActivity::class.java)

                intent.putExtra("UUID_EXTRA", uuid)

                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor, digite um UUID.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}