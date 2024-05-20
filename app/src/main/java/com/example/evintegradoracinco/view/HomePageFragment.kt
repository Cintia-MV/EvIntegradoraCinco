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

    private lateinit var hPBinding: FragmentHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       hPBinding = FragmentHomePageBinding.inflate(inflater, container, false)
        return hPBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hPBinding.imgArnold.setOnClickListener{
            view.findNavController().navigate(R.id.action_homePageFragment2_to_profileFragment)
        }

        hPBinding.btnEnviarDinP5.setOnClickListener {
            view.findNavController().navigate(R.id.action_homePageFragment2_to_sendFragment)
        }

        hPBinding.btnIngresarDinP5.setOnClickListener {
            view.findNavController().navigate(R.id.action_homePageFragment2_to_requestFragment)
        }
    }



}