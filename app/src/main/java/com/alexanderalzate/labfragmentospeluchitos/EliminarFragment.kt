package com.alexanderalzate.labfragmentospeluchitos


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_eliminar.view.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class EliminarFragment : Fragment() {

    var interfaz :Comunicador ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_eliminar, container, false)

        view.bnEliminar.setOnClickListener{
            var nombre = view.etEliminarNombre.text.toString()
            if(nombre!=""){
                interfaz?.enviarEliminar(nombre)
            } else{
                interfaz?.enviarEliminar("")
            }

        }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            interfaz = context!! as Comunicador
        }catch (e: Exception){
            Log.d("exception",e.toString())
        }

    }

}

