package com.example.evintegradoracinco.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.model.Usuario
import com.example.evintegradoracinco.model.UsuarioProveedor

// Enum que representa los diferentes errores que pueden ocurrir durante el login.
enum class ErroresLogin{
    emailNoValido,
    claveNoValida,
    credencialesIncorrectas
}


class LoginViewModel : ViewModel() {

    // LiveData para observar el estado de la validación
    private val _loginError = MutableLiveData<ErroresLogin?>()
    val loginError: LiveData<ErroresLogin?> get() = _loginError

    // Método para validar las credenciales del usuario.
    fun validarUsuario(email: String, password: String) {
        // Validar email
        if (!validarEmail(email)) {
            _loginError.value = ErroresLogin.emailNoValido
            return
        }
        // Validar constraseña
        if (!validarClave(password)) {
            _loginError.value = ErroresLogin.claveNoValida
            return
        }

        // Validar credenciales con la lista de usuarios.
        val usuarioValido = UsuarioProveedor.usuarios.any {
            it.email == email && it.clave == password
        }
        // Actualizar el estado de _loginError dependiendo de la validación.
        _loginError.value = if (usuarioValido) null else ErroresLogin.credencialesIncorrectas
    }

    // Método privado para validar el formato del email.
    private fun validarEmail(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
        return emailRegex.matches(email)
    }

    // Método privado para validar la longitud de la clave.
    private fun validarClave(clave: String): Boolean {
        return clave.length >= 6
    }
}