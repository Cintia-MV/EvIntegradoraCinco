package com.example.evintegradoracinco.model



data class Usuario(val email:String?, val clave: String?) {
    companion object {
        // Valores predeterminados
        val emailUsuario = "arnold.j@mail.com"
        val claveUsuario = "arnold123"
    }


}