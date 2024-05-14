package com.example.evintegradoracinco.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.databinding.FragmentSingUpBinding


class SingUpFragment : Fragment() {

    private lateinit var sUPBinding : FragmentSingUpBinding

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

        //Al dar clic en crear cuenta navega hacía el login
        sUPBinding.btnCrearP4.setOnClickListener {

            view.findNavController().navigate(R.id.action_singUpFragment_to_loginFragment)
        }

        //Al dar clic en ya tienes cuenta? navega hacia el login
        sUPBinding.loginP4.setOnClickListener{
            view.findNavController().navigate(R.id.action_singUpFragment_to_loginFragment)
        }


    }

}