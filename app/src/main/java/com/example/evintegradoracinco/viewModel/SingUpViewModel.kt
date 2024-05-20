package com.example.evintegradoracinco.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evintegradoracinco.model.Usuario
import com.example.evintegradoracinco.model.UsuarioProveedor

class SingUpViewModel : ViewModel() {
    fun agregarUsuario(nombre: String, apellido: String, email: String, clave: String): Boolean {
        val nuevoUsuario = Usuario(nombre, apellido, email, clave)
        return UsuarioProveedor.agregarUsuario(nuevoUsuario)
    }

    // Declaración de MutableLiveData para controlar la visibilidad del error
    val showError = MutableLiveData<Boolean>()

    // Método para cambiar la visibilidad del error
    fun setShowError(visible: Boolean) {
        showError.value = visible
    }
}