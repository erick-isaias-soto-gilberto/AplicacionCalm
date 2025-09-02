package com.coffeberak.calm.fragments.zona_activa_actividades

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.coffeberak.calm.R

class Pantalla_cartas_hacia_el_futuro : AppCompatActivity() {

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_cartas_hacia_el_futuro)

        editText = findViewById(R.id.editTextTextMultiLine)

        // Cargar el texto guardado al iniciar la actividad
        cargarTextoGuardado()

        // Encuentra el botón imageReturn
        val imageReturnButton = findViewById<ImageButton>(R.id.imageButtonReturn)

        // Agrega un OnClickListener al botón
        imageReturnButton.setOnClickListener {
            finish() // Finaliza la actividad actual
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onPause() {
        super.onPause()
        // Guardar el texto cuando la actividad se pausa o se cierra
        guardarTexto()
    }

    private fun guardarTexto() {
        val sharedPreferences = getSharedPreferences("datosCarta", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("textoCarta", editText.text.toString())
        editor.apply()
    }

    private fun cargarTextoGuardado() {
        val sharedPreferences = getSharedPreferences("datosCarta", Context.MODE_PRIVATE)
        val textoGuardado = sharedPreferences.getString("textoCarta", "")
        editText.setText(textoGuardado)
    }
}