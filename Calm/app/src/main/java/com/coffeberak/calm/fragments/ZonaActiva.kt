package com.coffeberak.calm.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.coffeberak.calm.R
import com.coffeberak.calm.fragments.zona_activa_actividades.Pantalla_Ejercicios_de_relajacion
import com.coffeberak.calm.fragments.zona_activa_actividades.Pantalla_Yoga
import com.coffeberak.calm.fragments.zona_activa_actividades.Pantalla_cartas_hacia_el_futuro
import com.coffeberak.calm.fragments.zona_activa_actividades.Tarea_prediccion

class ZonaActiva : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_zonaactica, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val botonImagen9 = view.findViewById<ImageButton>(R.id.imageButton9)

        botonImagen9.setOnClickListener {
            val intent = Intent(requireActivity(), Pantalla_cartas_hacia_el_futuro::class.java)
            startActivity(intent)
        }
        val botonImagen10 = view.findViewById<ImageButton>(R.id.imageButton10)

        botonImagen10.setOnClickListener {
            val intent = Intent(requireActivity(), Tarea_prediccion::class.java)
            startActivity(intent)
        }
        val botonImagen8 = view.findViewById<ImageButton>(R.id.imageButton8)

        botonImagen8.setOnClickListener {
            val intent = Intent(requireActivity(), Pantalla_Yoga::class.java)
            startActivity(intent)
        }

        val botonImagen7 = view.findViewById<ImageButton>(R.id.imageButton7)

        botonImagen7.setOnClickListener {
            val intent = Intent(requireActivity(), Pantalla_Ejercicios_de_relajacion::class.java)
            startActivity(intent)
        }
    }
}