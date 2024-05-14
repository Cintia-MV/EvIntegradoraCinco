package com.example.evintegradoracinco.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.databinding.FragmentLoginSingUpBinding


class LoginSingUpFragment : Fragment() {

    private lateinit var lSUFBinding : FragmentLoginSingUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        lSUFBinding = FragmentLoginSingUpBinding.inflate(inflater, container, false)

        return lSUFBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lSUFBinding.text2p2.setOnClickListener{

            view.findNavController().navigate(R.id.action_loginSingUpFragment_to_loginFragment)
        }


        lSUFBinding.btnP2.setOnClickListener {

            view.findNavController().navigate(R.id.action_loginSingUpFragment_to_singUpFragment)

        }
    }



}