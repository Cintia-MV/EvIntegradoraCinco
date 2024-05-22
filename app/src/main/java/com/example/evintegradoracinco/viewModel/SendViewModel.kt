package com.example.evintegradoracinco.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ErroresTransferencia {
    MONTO_INVALIDO,
    NOTA_INVALIDA
}
class SendViewModel : ViewModel() {
    // LiveData para observar el estado de los errores
    private val _transferenciaError = MutableLiveData<ErroresTransferencia?>()
    val transferenciaError: LiveData<ErroresTransferencia?> = _transferenciaError

    // LiveData para observar el éxito
    private val _transferenciaExitosa = MutableLiveData<Boolean>()
    val transferenciaExitosa: LiveData<Boolean> = _transferenciaExitosa

    // Método para validar la transferencia.
    fun validarTransferencia(monto:String, nota: String){
        if (!validarMonto(monto)){
            _transferenciaError.value = ErroresTransferencia.MONTO_INVALIDO
            return
        }

        if (!validarNota(nota)){
            _transferenciaError.value = ErroresTransferencia.NOTA_INVALIDA
            return
        }

        //Si todos los campos son válidos, realizar la transferencia cómo exitosa
        _transferenciaError.value = null
        _transferenciaExitosa.value = true
    }

    private fun validarMonto(monto: String): Boolean{
        val montoRegex = Regex("^[0-9]+(\\.[0-9]{1,2})?\$")
        return montoRegex.matches(monto)
    }

    private fun validarNota(nota: String):Boolean{
        return nota.isNotBlank()
    }
}