package com.alexanderalzate.labfragmentospeluchitos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_inventario.view.*

/**
 * A simple [Fragment] subclass.
 */
class InventarioFragment : Fragment() {

    private lateinit var peluche:Peluchito
    var listPeluches:ArrayList<Peluchito> = ArrayList()

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view =  inflater.inflate(R.layout.fragment_inventario, container, false)
        if(arguments!=null){
            listPeluches = arguments!!.getParcelableArrayList(ARG_PARAM)!!

            recyclerView = view.findViewById(R.id.recycler) as RecyclerView
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)

            val peluchitosAdapter = PeluchitosAdapter(listPeluches, view.context)
            recyclerView.adapter = peluchitosAdapter
        }
        return view
    }
    companion object{
        private val ARG_PARAM = "MyObject"

        fun newInstance(listpeluches:ArrayList<Peluchito>):InventarioFragment{
            val inventarioFragment = InventarioFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_PARAM,listpeluches)
            inventarioFragment.arguments = args
            return inventarioFragment
        }
    }
}