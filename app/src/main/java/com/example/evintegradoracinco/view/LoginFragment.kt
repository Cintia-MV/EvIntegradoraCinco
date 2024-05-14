package com.example.evintegradoracinco.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.databinding.FragmentLoginBinding



class LoginFragment : Fragment(){

    private lateinit var lFBinding : FragmentLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        lFBinding = FragmentLoginBinding.inflate(inflater, container, false)



        // Return the root view of the binding
        return lFBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lFBinding.btnP3.setOnClickListener {

            //Agregar código cuando tenga lista la vista siguiente
        }

        lFBinding.crearCtaP3.setOnClickListener{
            view.findNavController().navigate(R.id.action_loginFragment_to_singUpFragment)
        }

    }



}