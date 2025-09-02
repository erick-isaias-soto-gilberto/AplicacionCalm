package com.coffeberak.calm.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coffeberak.calm.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Diario : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    private val userList = mutableListOf<UserProfile>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_diario, container, false)
        recyclerView = view.findViewById(R.id.recyclerView) // Asegúrate de tener un RecyclerView en tu layout
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserAdapter(userList)
        recyclerView.adapter = adapter

        leerDatosDeUsuarios()

        return view
    }

    private fun leerDatosDeUsuarios() {
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.reference.child("users")

        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (userSnapshot in snapshot.children) {
                    val user = userSnapshot.getValue(UserProfile::class.java)
                    user?.let { userList.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Diario", "Error al leer datos de usuarios", error.toException())
            }
        })
    }
}

class UserAdapter(private val userList: List<UserProfile>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNombre: TextView = itemView.findViewById(R.id.textViewNombre)
        val textViewApellido: TextView = itemView.findViewById(R.id.textViewApellido)
        val textViewFechaNacimiento: TextView = itemView.findViewById(R.id.textViewFechaNacimiento)
        val textViewTelefono: TextView = itemView.findViewById(R.id.textViewTelefono)
        val textViewCorreo: TextView = itemView.findViewById(R.id.textViewCorreo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.textViewNombre.text = user.nombre
        holder.textViewApellido.text = user.apellido
        holder.textViewFechaNacimiento.text = user.fechaNacimiento
        holder.textViewTelefono.text = user.telefono
        holder.textViewCorreo.text = user.correo
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}