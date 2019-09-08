package com.alexanderalzate.labfragmentospeluchitos


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_agregar.view.*
import java.lang.Exception
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 */
class AgregarFragment : Fragment() {

    var interfaz:Comunicador?=null
    private lateinit var recyclerView: RecyclerView
    var peluchito:ArrayList<Peluchito> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_agregar, container, false)

        view.bnAgregar.setOnClickListener {

            var id = view.etId.text.toString()
            var nombre = view.etNombre.text.toString()
            var cantidad = view.etCantidad.text.toString()
            var precio = view.etPrecio.text.toString()

            if(id!="" && nombre!="" && cantidad!="" && precio!="") {
                interfaz?.enviarDatos(id, nombre, cantidad, precio)
                peluchito.add(
                    Peluchito(
                        id,
                        nombre,
                        cantidad,
                        precio
                    )
                )

                recyclerView = view.findViewById(R.id.recycler) as RecyclerView
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager =
                    LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)

                val peluchitosAdapter = PeluchitosAdapter(peluchito!!, view.context)
                recyclerView.adapter = peluchitosAdapter
            }else{
                interfaz?.enviarDatos("", "", "", "")
            }
        }

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            interfaz = context!! as Comunicador
        }catch (e:Exception){
            Log.d("exception",e.toString())
        }

    }


}
