package com.alexanderalzate.labfragmentospeluchitos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.peluchitos_item.view.*

class PeluchitosAdapter : RecyclerView.Adapter<PeluchitosAdapter.PeluchitosViewHolder>{

    private var listPeluchito:List<Peluchito>?=null
    private var context:Context?=null

    constructor(listPeluchito:List<Peluchito>,context: Context){
        this.listPeluchito = listPeluchito
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeluchitosViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.peluchitos_item,parent,false)
        return PeluchitosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPeluchito?.size!!
    }

    override fun onBindViewHolder(holder: PeluchitosViewHolder, position: Int) {
        var peluchito = listPeluchito!![position]
        holder.loadItem(peluchito)
    }



    class PeluchitosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        //,View.OnClickListener
        private var peluchito:Peluchito?= null

        /*init {
            itemView.setOnClickListener(this)
        }*/

        fun loadItem(peluchito: Peluchito){
            itemView.tvID.text = peluchito.id
            itemView.tvNombre.text = peluchito.nombre
            itemView.tvCantidad.text = peluchito.cantidad
            itemView.tvPrecio.text = peluchito.precio
        }
        /* override fun onClick(p0: View?) {
             val context = p0?.context
             val intent = Intent(context, MainActivity::class.java)
             intent.putExtra("peluchito",peluchito)
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
             context?.startActivity(intent)
         }*/
    }
}