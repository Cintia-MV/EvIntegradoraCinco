package com.example.evintegradoracinco.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    // Instancia del binding
    private lateinit var pBinding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño
        pBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return pBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Botón volver para volver a la pagina principal de las transferencias
        pBinding.volver.setOnClickListener{
            view.findNavController().navigate(R.id.action_profileFragment_to_homePageFragment2)
        }

        //Botón para "cerrar sesión"
        pBinding.salir.setOnClickListener{
            val intent = Intent(requireContext(), ActivityLoginSingUp::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

}