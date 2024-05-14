package com.example.evintegradoracinco.model

import android.util.Patterns

class Usuario(private val email:String?, private val clave: String?) {

    fun getEmail(): String{
        return email ?: ""
    }

    fun getClave(): String{
        return clave?: ""
    }

    val emailValido: Boolean
        get() = Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()

    val claveValida: Boolean
        get() = getClave().length>5



}