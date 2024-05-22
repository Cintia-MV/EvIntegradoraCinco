package com.example.evintegradoracinco.model

// Clase que actúa como proveedor de usuarios, conteniendo una lista de usuarios y métodos para gestionarla
class UsuarioProveedor {

    companion object {
        // Lista mutable que almacena instancias de Usuario.
        val usuarios = mutableListOf(
            Usuario(
                nombre = "Arnold",
                apellido = "Shortman",
                email = "arnold.s@mail.com",
                clave = "arnold123"
            )
        )

        // Método para agregar un nuevo usuario a la lista.
        fun agregarUsuario(usuario: Usuario): Boolean {
            if (usuarios.any { it.email == usuario.email }) {
                return false // El usuario ya existe
            }
            usuarios.add(usuario)
            return true
        }

    }
}