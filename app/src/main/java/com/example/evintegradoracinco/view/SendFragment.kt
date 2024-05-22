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

    //Intancia de viewBinding
    private lateinit var sBinding: FragmentSendBinding
    //Instancia de viewModel
    private lateinit var viewModel: SendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflar vista con viewbinding
        sBinding = FragmentSendBinding.inflate(inflater,container,false)
        return sBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inicializar el ViewModel
        viewModel = ViewModelProvider(this).get(SendViewModel::class.java)
        sBinding.viewModel = viewModel
        sBinding.lifecycleOwner = viewLifecycleOwner

        // Observar los errores de transferencia y manejarlos.
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

        // Observar el éxito de la transferencia y manejarlo.
        viewModel.transferenciaExitosa.observe(viewLifecycleOwner){exito ->
            if (exito){
                mostrarMensajeExito("Transferencia realizada con éxito")
                // Retraso para mostrar el mensaje de éxito antes de navegar de regreso a la página de inicio.
                CoroutineScope(Dispatchers.Main).launch {
                    delay(3000)
                    view.findNavController().navigate(R.id.action_sendFragment_to_homePageFragment2)
                }

            }
        }

        // Botón "Enviar" para realizar la validación del monto y la nota.
        sBinding.button.setOnClickListener {
            val monto = sBinding.ingresarMonto.text.toString()
            val nota = sBinding.notaEnviar.text.toString()
            viewModel.validarTransferencia(monto, nota)


        }

        //Botón para volver a la página principal
        sBinding.volverSend.setOnClickListener{
            view.findNavController().navigate(R.id.action_sendFragment_to_homePageFragment2)
        }

    }

    // Función para mostrar mensajes de error en el texto de la vista
    private fun mostrarMensajeError(mensaje: String){
        sBinding.errorTextView.text = mensaje
        sBinding.errorTextView.visibility = View.VISIBLE
    }

    // Función para mostrar mensajes de éxito en un Toast y ocultar el texto de error.
    private fun mostrarMensajeExito(mensaje:String){
        sBinding.errorTextView.visibility = View.GONE
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show()
    }


}