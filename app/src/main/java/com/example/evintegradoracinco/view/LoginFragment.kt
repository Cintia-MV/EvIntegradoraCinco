package com.example.evintegradoracinco.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.databinding.FragmentLoginBinding
import com.example.evintegradoracinco.viewModel.ErroresLogin
import com.example.evintegradoracinco.viewModel.LoginViewModel


class LoginFragment : Fragment() {

    private lateinit var lFBinding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        lFBinding = FragmentLoginBinding.inflate(inflater, container, false)

        return lFBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        lFBinding.viewModel = viewModel
        lFBinding.lifecycleOwner = viewLifecycleOwner


        lFBinding.btnP3.setOnClickListener {
            val email = lFBinding.hintEmailP3.text.toString()
            val password = lFBinding.claveHintP3.text.toString()

            val loginError = viewModel.validarUsuario(email, password)
            loginError?.let {
                val mensajeDeError = when (it){
                    ErroresLogin.emailNoValido -> "Por favor, introduce un correo electrónico válido."
                    ErroresLogin.claveNoValida -> "La contraseña debe tener al menos 6 caracteres."
                    ErroresLogin.credencialesIncorrectas -> "Email o contraseña incorrectos."
            }
                lFBinding.errorTextView.text = mensajeDeError
                lFBinding.errorTextView.visibility = View.VISIBLE
        }?: run {
                // Si no hay errores, continuar con la navegación
                lFBinding.errorTextView.visibility = View.GONE // Ocultar el TextView de error si no hay error
                Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                //Intent para ir a la siguiente vista
                val intent = Intent(requireContext(), HomePageActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
    }

    }

}
