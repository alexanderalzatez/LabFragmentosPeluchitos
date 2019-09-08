package com.alexanderalzate.labfragmentospeluchitos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_buscar.*
import kotlinx.android.synthetic.main.fragment_buscar.view.*

/**
 * A simple [Fragment] subclass.
 */
class BuscarFragment : Fragment() {

    private lateinit var peluche: Peluchito
    var listPeluches: ArrayList<Peluchito> = ArrayList()
    var pelucheBuscado:ArrayList<Peluchito> = ArrayList()
    var data = ""
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view =  inflater.inflate(R.layout.fragment_buscar, container, false)
        if (arguments != null) {
            listPeluches = arguments!!.getParcelableArrayList(ARG_PARAM)!!

            view.bnBuscar.setOnClickListener {

                for (peluche in listPeluches) {

                    if (peluche.nombre == view.etBuscarNombre.text.toString()) {
                        pelucheBuscado.add(peluche)
                        view.tvResultBuscar.text = "Busqueda Exitosa!"
                        break
                    }else{
                        view.tvResultBuscar.text = "No se encontró lo que buscabas!"
                    }
                }
                recyclerView = view.findViewById(R.id.recycler) as RecyclerView
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)

                val peluchitosAdapter = PeluchitosAdapter(pelucheBuscado, view.context)
                recyclerView.adapter = peluchitosAdapter

                pelucheBuscado = ArrayList()
            }



        }
        return view
    }
    companion object{
        private val ARG_PARAM = "MyObject"

        fun newInstance(listpeluches:ArrayList<Peluchito>):BuscarFragment{
            val buscarFragment = BuscarFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_PARAM,listpeluches)
            buscarFragment.arguments = args
            return buscarFragment
        }
    }
}
