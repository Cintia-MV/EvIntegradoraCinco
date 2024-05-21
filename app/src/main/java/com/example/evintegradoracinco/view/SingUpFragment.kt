package com.example.evintegradoracinco.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.databinding.FragmentSingUpBinding
import com.example.evintegradoracinco.viewModel.ErroresSignUp
import com.example.evintegradoracinco.viewModel.SingUpViewModel


class SingUpFragment : Fragment() {

    private lateinit var sUPBinding: FragmentSingUpBinding
    private lateinit var viewModel: SingUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sUPBinding = FragmentSingUpBinding.inflate(inflater, container, false)
        return sUPBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SingUpViewModel::class.java)
        sUPBinding.viewModel = viewModel
        sUPBinding.lifecycleOwner = viewLifecycleOwner

        viewModel.signUpError.observe(viewLifecycleOwner){error ->
            error?.let{
                when (it){
                    ErroresSignUp.NOMBRE_INVALIDO -> mostrarMensajeError("Debe ingresar nombre")
                    ErroresSignUp.APELLIDO_INVALIDO -> mostrarMensajeError("Debe ingresar apellido")
                    ErroresSignUp.EMAIL_INVALIDO -> mostrarMensajeError("Ingresar correo electrónico válido")
                    ErroresSignUp.CLAVE_INVALIDA -> mostrarMensajeError("La clave debe ser mayor a 6 caracteres")
                }
            }

        }

        viewModel.signUpSuccess.observe(viewLifecycleOwner){success ->
            if (success){
                mostrarMensajeExito("Usuario registrado con éxito")
                view.findNavController().navigate(R.id.action_singUpFragment_to_loginFragment)
            }
        }

        //Al dar clic en crear cuenta navega hacía el login
        sUPBinding.btnCrearP4.setOnClickListener {
            val nombre = sUPBinding.hintNombreP4.text.toString()
            val apellido = sUPBinding.hintApellP4.text.toString()
            val email = sUPBinding.hintEmailP4.text.toString()
            val clave = sUPBinding.hintClaveP4.text.toString()
            val confirmaClave = sUPBinding.reingClaveP4.text.toString()

            // Verificar que las claves coincidan
            if (clave != confirmaClave) {
                mostrarMensajeError("Las claves no coinciden")
                return@setOnClickListener // Detener el flujo si las claves no coinciden
            }

            // Registrar el usuario
            viewModel.agregarUsuario(nombre,apellido, email, clave)
        }
    }

    private fun mostrarMensajeError(mensaje: String) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show()
    }

    private fun mostrarMensajeExito(mensaje: String) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show()
    }

}


