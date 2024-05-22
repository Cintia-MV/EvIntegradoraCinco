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
    //Instancia de viewBinding
    private lateinit var lFBinding: FragmentLoginBinding
    //Instancia de viewModel
    private lateinit var viewModel: LoginViewModel


    //Inflar la vista
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        lFBinding = FragmentLoginBinding.inflate(inflater, container, false)

        return lFBinding.root
    }


    //// Configura la vista y el ViewModel.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inicializa el ViewModel.
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // Asigna el ViewModel al binding y establece el lifecycleOwner.
        lFBinding.viewModel = viewModel
        lFBinding.lifecycleOwner = viewLifecycleOwner

        // Observa los cambios en los errores de login.
        viewModel.loginError.observe(viewLifecycleOwner){ error ->
            when(error){
                ErroresLogin.emailNoValido -> {
                    lFBinding.errorTextView.text = "Ingresar correo electrónico válido"
                    lFBinding.errorTextView.visibility = View.VISIBLE
                }
                ErroresLogin.claveNoValida ->{
                    lFBinding.errorTextView.text = "La contraseña debe tener almenos 6 caracteres"
                    lFBinding.errorTextView.visibility = View.VISIBLE
                }
                ErroresLogin.credencialesIncorrectas -> {
                    lFBinding.errorTextView.text = "Email o contraseña incorrectos"
                    lFBinding.errorTextView.visibility = View.VISIBLE
                }
                null -> {
                    lFBinding.errorTextView.visibility = View.GONE
                    Toast.makeText(requireContext(), "¡Sesión iniciada con éxito!", Toast.LENGTH_LONG).show()
                    // Navega a la actividad principal una vez iniciada la sesión.
                    val intent = Intent(requireContext(), HomePageActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
        }

        //Botón para iniciar sesión
        lFBinding.btnP3.setOnClickListener {
            val  email = lFBinding.hintEmailP3.text.toString()
            val clave = lFBinding.claveHintP3.text.toString()
            viewModel.validarUsuario(email, clave)
        }


        //Botón para crear una nueva cuenta
        lFBinding.crearCtaP3.setOnClickListener{
            view.findNavController().navigate(R.id.action_loginFragment_to_singUpFragment)
        }

    }

}