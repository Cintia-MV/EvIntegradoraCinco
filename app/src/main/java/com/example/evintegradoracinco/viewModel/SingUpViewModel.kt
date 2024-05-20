package com.example.evintegradoracinco.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evintegradoracinco.model.Usuario
import com.example.evintegradoracinco.model.UsuarioProveedor

enum class ErroresSignUp {
    NOMBRE_INVALIDO,
    APELLIDO_INVALIDO,
    EMAIL_INVALIDO,
    CLAVE_INVALIDA
}

class SingUpViewModel : ViewModel() {

    fun agregarUsuario(nombre: String, apellido: String, email: String, clave: String): ErroresSignUp? {
        // Validar los campos de entrada
        if (!validarNombre(nombre)) {
            return ErroresSignUp.NOMBRE_INVALIDO
        }
        if (!validarApellido(apellido)) {
            return ErroresSignUp.APELLIDO_INVALIDO
        }
        if (!validarEmail(email)) {
            return ErroresSignUp.EMAIL_INVALIDO
        }
        if (!validarClave(clave)) {
            return ErroresSignUp.CLAVE_INVALIDA
        }

        // Si todos los campos son válidos, crear un nuevo usuario y agregarlo
        val nuevoUsuario = Usuario(nombre, apellido, email, clave)
        UsuarioProveedor.agregarUsuario(nuevoUsuario)

        // Retornar null si no hay errores
        return null
    }

    private fun validarNombre(nombre: String): Boolean {
        val regex = Regex("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")
        return nombre.isNotBlank() && nombre.matches(regex)
    }

    private fun validarApellido(apellido: String): Boolean {
        val regex = Regex("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s'-]+")
        return apellido.isNotBlank() && apellido.matches(regex)
    }

    private fun validarEmail(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
        return emailRegex.matches(email)
    }

    private fun validarClave(clave: String): Boolean {
        return clave.length >= 6
    }
}