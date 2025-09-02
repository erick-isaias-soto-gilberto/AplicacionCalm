package com.coffeberak.calm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.coffeberak.calm.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class EspacioTerapeutico : Fragment() {

    private lateinit var editTextNombre: EditText
    private lateinit var editTextApellido: EditText
    private lateinit var editTextFechaNacimiento: EditText
    private lateinit var editTextTelefono: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_espacioterapeutico, container, false)

        editTextNombre = view.findViewById(R.id.editTextNombre)
        editTextApellido = view.findViewById(R.id.editTextApellido)
        editTextFechaNacimiento = view.findViewById(R.id.editTextFechaNacimiento)
        editTextTelefono = view.findViewById(R.id.editTextTelefono)

        val buttonGuardar = view.findViewById<Button>(R.id.buttonGuardar)
        buttonGuardar.setOnClickListener {
            guardarPerfil()
        }

        return view
    }

    private fun guardarPerfil() {
        val user = FirebaseAuth.getInstance().currentUser
        val database = FirebaseDatabase.getInstance()
        val userRef = database.reference.child("users").child(user?.uid ?: "")

        val nombre = editTextNombre.text.toString()
        val apellido = editTextApellido.text.toString()
        val fechaNacimiento = editTextFechaNacimiento.text.toString()
        val telefono = editTextTelefono.text.toString()

        val profile = UserProfile(nombre, apellido, fechaNacimiento, telefono, user?.email)

        userRef.setValue(profile)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Perfil actualizado", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Error al actualizar el perfil", Toast.LENGTH_SHORT).show()
            }
    }
}

data class UserProfile(
    val nombre: String? = null,
    val apellido: String? = null,
    val fechaNacimiento: String? = null,
    val telefono: String? = null,
    val correo: String? = null
)