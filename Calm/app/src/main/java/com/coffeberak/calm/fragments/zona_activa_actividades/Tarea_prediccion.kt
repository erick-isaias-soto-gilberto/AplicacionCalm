package com.coffeberak.calm.fragments.zona_activa_actividades

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.coffeberak.calm.R
import android.widget.EditText
import android.widget.ImageButton



class Tarea_prediccion : AppCompatActivity() {


    private val idsEditText = listOf(
        R.id.editTextText15, R.id.editTextText16, R.id.editTextText17, R.id.editTextText18,
        R.id.editTextText19, R.id.editTextText20, R.id.editTextText21,
        R.id.editTextText23, R.id.editTextText24, R.id.editTextText25, R.id.editTextText26,
        R.id.editTextText27, R.id.editTextText28, R.id.editTextText29
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tarea_prediccion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cargarDatosEditText()
        // Encuentra el botón imageReturn
        val imageReturnButton = findViewById<ImageButton>(R.id.imageButton2) // Reemplaza con el ID correcto

        // Agrega un OnClickListener al botón
        imageReturnButton.setOnClickListener {
            finish() // Finaliza la actividad actual
        }
    }

    override fun onPause() {
        super.onPause()
        guardarDatosEditText()
    }

    private fun guardarDatosEditText() {
        val sharedPreferences = getSharedPreferences("DatosTareaPrediccion", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        for (id in idsEditText) {
            val editText = findViewById<EditText>(id)
            editor.putString(id.toString(), editText.text.toString())
        }
        editor.apply()
    }

    private fun cargarDatosEditText() {
        val sharedPreferences = getSharedPreferences("DatosTareaPrediccion", Context.MODE_PRIVATE)
        for (id in idsEditText) {
            val editText = findViewById<EditText>(id)
            val textoGuardado = sharedPreferences.getString(id.toString(), "")
            editText.setText(textoGuardado)
        }
    }
}