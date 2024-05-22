package com.example.evintegradoracinco.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.databinding.FragmentHomePageBinding


class HomePageFragment : Fragment() {
    //Instancia de viewBinding para FragmentHomePageBinding
    private lateinit var hPBinding: FragmentHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    //Inflar la vista con binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       hPBinding = FragmentHomePageBinding.inflate(inflater, container, false)
        return hPBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Clic en la imagen para navegar hacia la configuración del perfil
        hPBinding.imgArnold.setOnClickListener{
            view.findNavController().navigate(R.id.action_homePageFragment2_to_profileFragment)
        }

        //Clic en botón enviar dinero para navegar hacia la transacción
        hPBinding.btnEnviarDinP5.setOnClickListener {
            view.findNavController().navigate(R.id.action_homePageFragment2_to_sendFragment)
        }

        hPBinding.btnIngresarDinP5.setOnClickListener {
            view.findNavController().navigate(R.id.action_homePageFragment2_to_requestFragment)
        }
    }



}