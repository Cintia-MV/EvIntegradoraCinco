package com.example.evintegradoracinco.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.evintegradoracinco.databinding.FragmentLoginBinding
import com.example.evintegradoracinco.viewModel.LoginViewModel
import com.example.evintegradoracinco.viewModel.ToastCallback

class LoginFragment : Fragment(), ToastCallback {

    private lateinit var lFBinding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        lFBinding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        lFBinding?.lifecycleOwner = this
        lFBinding?.viewModel = viewModel
        viewModel.llamadaToast = this
        return lFBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtiene una referencia al NavController
        val navController = findNavController()

        // Configura el NavController en el ViewModel
        viewModel.setNavController(navController)

        lFBinding.btnP3.setOnClickListener {
            viewModel.loginClick()
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
