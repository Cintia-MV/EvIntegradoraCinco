package com.example.evintegradoracinco.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.evintegradoracinco.model.Usuario


// para agregar un toast y mostarlo en la vista
interface ToastCallback {
    fun showToast(message: String)
}

class LoginViewModel : ViewModel(), ToastCallback{

    //Observadores
    var email = MutableLiveData<String?>()
    var clave = MutableLiveData<String?>()

    //Observadores para comunidarme con la vista
    var emailError = MutableLiveData<String?>()
    var claveError = MutableLiveData<String?>()
    private var usuarioMutableLiveData: MutableLiveData<Usuario>? = null

    //llamar al toast
    var llamadaToast: ToastCallback? = null

    init {
        usuarioMutableLiveData = MutableLiveData()
    }

    fun loginClick(){
        emailError.value = null
        claveError.value = null

        val usuario = Usuario(email.getValue(), clave.getValue())
        if (email.getValue() == null || email.getValue()!!.isEmpty()){
            emailError.value = "Ingrese correo electrónico."
            return
        }

        if (!usuario.emailValido){
            emailError.value = "Ingrese un correo electrónico válido."
            return
        }

        if (!usuario.claveValida){
            claveError.value = "La contraseña debe contener 5 caracteres."
            return
        }


        usuarioMutableLiveData!!.value = usuario
        showToast("Datos incorrectos")

        //Si los datos son ingresados correctamente, deja los campos en blanco
        email.value = null
        clave.value = null
    }

    val usuario: LiveData<Usuario>
        get() {
            if(usuarioMutableLiveData == null){
                usuarioMutableLiveData = MutableLiveData()
            }
            return usuarioMutableLiveData!!
        }

    override fun showToast(message: String) {
       llamadaToast?.showToast(message)
    }

}