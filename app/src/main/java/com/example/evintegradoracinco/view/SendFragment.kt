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
import com.example.evintegradoracinco.databinding.FragmentSendBinding
import com.example.evintegradoracinco.viewModel.ErroresTransferencia
import com.example.evintegradoracinco.viewModel.SendViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SendFragment : Fragment() {

    private lateinit var sBinding: FragmentSendBinding
    private lateinit var viewModel: SendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sBinding = FragmentSendBinding.inflate(inflater,container,false)
        return sBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SendViewModel::class.java)
        sBinding.viewModel = viewModel
        sBinding.lifecycleOwner = viewLifecycleOwner

        viewModel.transferenciaError.observe(viewLifecycleOwner){error ->
            error?.let {
                val mensajeError = when(it){
                    ErroresTransferencia.MONTO_INVALIDO -> "Ingrese un monto válido"
                    ErroresTransferencia.NOTA_INVALIDA -> "La nota no puede estar vacía"
                    else -> ""
                }
                mostrarMensajeError(mensajeError)
            }
        }

        viewModel.transferenciaExitosa.observe(viewLifecycleOwner){exito ->
            if (exito){
                mostrarMensajeExito("Transferencia realizada con éxito")
                CoroutineScope(Dispatchers.Main).launch {
                    delay(3000)
                    view.findNavController().navigate(R.id.action_sendFragment_to_homePageFragment2)
                }

            }
        }

        sBinding.button.setOnClickListener {
            val monto = sBinding.ingresarMonto.text.toString()
            val nota = sBinding.notaEnviar.text.toString()
            viewModel.validarTransferencia(monto, nota)


        }


        sBinding.volverSend.setOnClickListener{
            view.findNavController().navigate(R.id.action_sendFragment_to_homePageFragment2)
        }

    }
    private fun mostrarMensajeError(mensaje: String){
        sBinding.errorTextView.text = mensaje
        sBinding.errorTextView.visibility = View.VISIBLE
    }

    private fun mostrarMensajeExito(mensaje:String){
        sBinding.errorTextView.visibility = View.GONE
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show()
    }


}