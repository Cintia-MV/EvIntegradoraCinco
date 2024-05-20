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
import com.example.evintegradoracinco.viewModel.SingUpViewModel


class SingUpFragment : Fragment() {

    private lateinit var sUPBinding : FragmentSingUpBinding
    private lateinit var viewModel : SingUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        sUPBinding = FragmentSingUpBinding.inflate(inflater, container,false)
        return sUPBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SingUpViewModel::class.java)
        sUPBinding.viewModel = viewModel
        sUPBinding.lifecycleOwner = viewLifecycleOwner




        //Al dar clic en crear cuenta navega hacía el login
        sUPBinding.btnCrearP4.setOnClickListener {

            //Capturo los datos ingresados en el formulario
            val nombre = sUPBinding.hintNombreP4.text.toString()
            val apellido = sUPBinding.hintApellP4.text.toString()
            val email = sUPBinding.hintEmailP4.text.toString()
            val clave = sUPBinding.hintClaveP4.text.toString()
            val confirmaClave = sUPBinding.reingClaveP4.text.toString()

            if (clave != confirmaClave) {
                Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            } else {
                val exito = viewModel.agregarUsuario(nombre, apellido, email, clave)
                if (exito) {
                    Toast.makeText(context, "Usuario creado con éxito", Toast.LENGTH_LONG).show()
                    view.findNavController().navigate(R.id.action_singUpFragment_to_loginFragment)
                } else {
                    Toast.makeText(context, "El usuario ya existe", Toast.LENGTH_LONG).show()
                }
            }
        }

        //Al dar clic en ya tienes cuenta? navega hacia el login
        sUPBinding.loginP4.setOnClickListener{
            view.findNavController().navigate(R.id.action_singUpFragment_to_loginFragment)
        }

    }




}


