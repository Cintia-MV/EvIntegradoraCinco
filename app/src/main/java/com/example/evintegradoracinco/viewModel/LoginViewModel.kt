package com.example.evintegradoracinco.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.model.Usuario

interface ToastCallback {
    fun showToast(message: String)
}

class LoginViewModel : ViewModel(), ToastCallback {

    var email = MutableLiveData<String?>()
    var clave = MutableLiveData<String?>()
    var emailError = MutableLiveData<String?>()
    var claveError = MutableLiveData<String?>()
    var usuarioMutableLiveData: MutableLiveData<Usuario>? = null
    var llamadaToast: ToastCallback? = null

    private lateinit var navController: NavController

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    init {
        usuarioMutableLiveData = MutableLiveData()
    }

    fun loginClick() {
        emailError.value = null
        claveError.value = null

        val usuario = Usuario(email.value, clave.value)
        if (email.value.isNullOrEmpty()) {
            emailError.value = "Ingrese correo electrónico."
            return
        }

        if (!usuario.emailValido) {
            emailError.value = "Ingrese un correo electrónico válido."
            return
        }

        if (!usuario.claveValida) {
            claveError.value = "La contraseña debe contener al menos 5 caracteres."
            return
        }

        navController.navigate(R.id.action_loginFragment_to_singUpFragment)

        usuarioMutableLiveData?.value = usuario
        llamadaToast?.showToast("Datos incorrectos")

        email.value = null
        clave.value = null
    }

    override fun showToast(message: String) {
        llamadaToast?.showToast(message)
    }
}