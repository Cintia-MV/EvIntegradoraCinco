package com.example.evintegradoracinco.viewModel

import androidx.lifecycle.LiveData
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

    private val _signUpError = MutableLiveData<ErroresSignUp?>()
    val signUpError: LiveData<ErroresSignUp?> get() = _signUpError

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean> get() = _signUpSuccess
    fun agregarUsuario(nombre: String, apellido: String, email: String, clave: String) {


        // Validar los campos de entrada
        if (!validarNombre(nombre)) {
            _signUpError.value = ErroresSignUp.NOMBRE_INVALIDO
            return
        }
        if (!validarApellido(apellido)) {
            _signUpError.value = ErroresSignUp.APELLIDO_INVALIDO
            return
        }
        if (!validarEmail(email)) {
            _signUpError.value = ErroresSignUp.EMAIL_INVALIDO
            return
        }
        if (!validarClave(clave)) {
            _signUpError.value = ErroresSignUp.CLAVE_INVALIDA
            return
        }

        // Si todos los campos son válidos, crear un nuevo usuario y agregarlo
        val nuevoUsuario = Usuario(nombre, apellido, email, clave)
        UsuarioProveedor.agregarUsuario(nuevoUsuario)

        // Retornar null si no hay errores
        _signUpError.value = null
        _signUpSuccess.value = true
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