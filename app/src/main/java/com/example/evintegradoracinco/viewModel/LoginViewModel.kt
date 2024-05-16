package com.example.evintegradoracinco.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.model.Usuario

enum class ErroresLogin{
    emailNoValido,
    claveNoValida,
    credencialesIncorrectas
}


class LoginViewModel : ViewModel() {

    fun validarUsuario(email: String, password: String): ErroresLogin? {
       return when{
           !validarEmail(email) -> ErroresLogin.emailNoValido
           !validarClave(password)-> ErroresLogin.claveNoValida
           email != Usuario.emailUsuario || password != Usuario.claveUsuario -> ErroresLogin.credencialesIncorrectas
           else -> null
       }
    }

    private fun validarEmail(email:String): Boolean{
        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
        return emailRegex.matches(email)
    }

    private fun validarClave(clave:String):Boolean{
        return clave.length >= 6
    }

}