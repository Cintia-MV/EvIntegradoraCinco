package com.example.evintegradoracinco.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.databinding.FragmentRequestBinding


class RequestFragment : Fragment() {

    private lateinit var rBinding: FragmentRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rBinding = FragmentRequestBinding.inflate(inflater, container, false)
        return rBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rBinding.volverRequest.setOnClickListener{
            view.findNavController().navigate(R.id.action_requestFragment_to_homePageFragment2)
        }

    }


}