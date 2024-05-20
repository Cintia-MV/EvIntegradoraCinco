package com.example.evintegradoracinco.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.model.Usuario
import com.example.evintegradoracinco.model.UsuarioProveedor

enum class ErroresLogin{
    emailNoValido,
    claveNoValida,
    credencialesIncorrectas
}


class LoginViewModel : ViewModel() {

    fun validarUsuario(email: String, password: String): ErroresLogin? {
        // Validar email y contraseña
        if (!validarEmail(email)) {
            return ErroresLogin.emailNoValido
        }
        if (!validarClave(password)) {
            return ErroresLogin.claveNoValida
        }

        // Validar credenciales
        val usuarioValido = UsuarioProveedor.usuarios.any {
            it.email == email && it.clave == password
        }
        return if (usuarioValido) null else ErroresLogin.credencialesIncorrectas
    }


    private fun validarEmail(email:String): Boolean{
        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
        return emailRegex.matches(email)
    }

    private fun validarClave(clave:String):Boolean{
        return clave.length >= 6
    }

}