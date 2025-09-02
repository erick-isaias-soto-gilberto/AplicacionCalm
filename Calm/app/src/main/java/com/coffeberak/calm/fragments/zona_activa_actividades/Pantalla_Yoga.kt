package com.coffeberak.calm.fragments.zona_activa_actividades

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.coffeberak.calm.R

class Pantalla_Yoga : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_yoga)


        val imageReturnButton = findViewById<ImageButton>(R.id.imageButton)


        imageReturnButton.setOnClickListener {
            finish()
        }


        val videoUrl = "https://www.youtube.com/watch?v=263Vb6xiifo"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        startActivity(intent)
    }
}