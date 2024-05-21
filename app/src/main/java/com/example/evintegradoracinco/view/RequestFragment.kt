package com.example.evintegradoracinco.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.evintegradoracinco.R
import com.example.evintegradoracinco.databinding.FragmentRequestBinding
import com.example.evintegradoracinco.viewModel.ErroresIngresoMonto
import com.example.evintegradoracinco.viewModel.RequestViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class RequestFragment : Fragment() {

    private lateinit var rBinding: FragmentRequestBinding
    private lateinit var viewModel: RequestViewModel

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
        viewModel = ViewModelProvider(this).get(RequestViewModel::class.java)
        rBinding.viewModel = viewModel
        rBinding.lifecycleOwner = viewLifecycleOwner

        viewModel.ingresoMontoError.observe(viewLifecycleOwner){error->
            error?.let {
                val mensajeError = when(it){
                    ErroresIngresoMonto.MONTO_INVALIDO -> "Ingrese un monto válido (valor numérico)"
                    ErroresIngresoMonto.NOTA_INVALIDA -> "La nota no puede estar vacía"
                    else -> ""
                }
                mostrarErrorMonto(mensajeError)
            }
        }

        viewModel.ingresoMontoExitoso.observe(viewLifecycleOwner){exito ->
            if(exito){
                mostrarExitoMonto("Ingreso realizado con éxito")
                CoroutineScope(Dispatchers.Main).launch {
                    delay(3000)
                    view.findNavController().navigate(R.id.action_requestFragment_to_homePageFragment2)
                }
            }
        }

        rBinding.button2.setOnClickListener {
            val monto = rBinding.ingresarDinero.text.toString()
            val nota = rBinding.notaIngresar.text.toString()
            viewModel.validarIngresoMonto(monto, nota)
        }


        rBinding.volverRequest.setOnClickListener{
            view.findNavController().navigate(R.id.action_requestFragment_to_homePageFragment2)
        }

    }

    private fun mostrarErrorMonto(mensaje:String){
        rBinding.errorTextView.text = mensaje
        rBinding.errorTextView.visibility = View.VISIBLE
    }

    private fun mostrarExitoMonto(mensaje: String){
        rBinding.errorTextView.visibility = View.VISIBLE
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show()
    }


}