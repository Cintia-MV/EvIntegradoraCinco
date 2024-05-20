package com.example.evintegradoracinco.model

class UsuarioProveedor {

    companion object {
        val usuarios = mutableListOf(
            Usuario(
                nombre = "Arnold",
                apellido = "Shortman",
                email = "arnold.s@mail.com",
                clave = "arnold123"
            )
        )

        fun agregarUsuario(usuario: Usuario): Boolean {
            if (usuarios.any { it.email == usuario.email }) {
                return false // El usuario ya existe
            }
            usuarios.add(usuario)
            return true
        }

    }
}