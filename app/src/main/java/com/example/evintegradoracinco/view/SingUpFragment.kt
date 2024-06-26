package com.example.evintegradoracinco.view

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.databinding.FragmentSingUpBinding
import com.example.evintegradoracinco.viewModel.ErroresSignUp
import com.example.evintegradoracinco.viewModel.SingUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
        // Inicializa el ViewModel.
        viewModel = ViewModelProvider(this).get(SingUpViewModel::class.java)
        sUPBinding.viewModel = viewModel
        sUPBinding.lifecycleOwner = viewLifecycleOwner

        // Observa los errores de registro.
        viewModel.signUpError.observe(viewLifecycleOwner){error ->
            error?.let{
                when (it){
                    ErroresSignUp.NOMBRE_INVALIDO -> mostrarMensajeError("Debe ingresar nombre", R.id.errorNombre)
                    ErroresSignUp.APELLIDO_INVALIDO -> mostrarMensajeError("Debe ingresar apellido", R.id.errorApellido)
                    ErroresSignUp.EMAIL_INVALIDO -> mostrarMensajeError("Ingresar correo electrónico válido", R.id.errorEmail)
                    ErroresSignUp.CLAVE_INVALIDA -> mostrarMensajeError("La clave debe ser mayor a 6 caracteres", R.id.errorClave)
                }
            }

        }

        // Observa el éxito del registro.
        viewModel.signUpSuccess.observe(viewLifecycleOwner){exito ->
            if (exito){
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
                mostrarMensajeError("Las claves no coinciden", R.id.errorClave2)
                return@setOnClickListener // Detener el flujo si las claves no coinciden
            }

            // Registrar el usuario
            viewModel.agregarUsuario(nombre,apellido, email, clave)
        }
    }

    // Método para mostrar un mensaje de error en un TextView.
    private fun mostrarMensajeError(mensaje: String, textViewId: Int) {
        val textView = view?.findViewById<TextView>(textViewId)

            textView?.text = mensaje
            textView?.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.Main).launch {
                delay(2000)
                textView?.visibility = View.GONE
            }
    }

    // Método para mostrar un mensaje de éxito mediante un Toast.
    private fun mostrarMensajeExito(mensaje: String) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show()
    }

}


