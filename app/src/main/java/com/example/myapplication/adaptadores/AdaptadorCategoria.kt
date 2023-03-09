package com.example.myapplication.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.clases.Categoria

class AdaptadorCategoria(context: Context?, private val listacategoria:List<Categoria>?): BaseAdapter() {
    private val layoutInflater: LayoutInflater
    init {
        layoutInflater=LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return listacategoria!!.size
    }

    override fun getItem(p0: Int): Any {
        return listacategoria!![p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var vista=p1
        if(vista==null){
            vista=layoutInflater.inflate(R.layout.elemento_lista_categoria,p2,false)
            val objcategoria=getItem(p0) as Categoria

            //creamos los controles
            val lblCodCat= vista!!.findViewById<TextView>(R.id.lstCodCat)
            val lblNomCat= vista!!.findViewById<TextView>(R.id.lstNomCat)
            val lblEstCat= vista!!.findViewById<TextView>(R.id.lstEstCat)
            //agregamos los valores a la lista
            lblCodCat.text=""+objcategoria.codigo
            lblNomCat.text=""+objcategoria.nombre
            if(objcategoria.estado==true){
                lblEstCat.text="Habilitado"
            }else{
                lblEstCat.text="Deshabilitado"
            }
        }
        return vista!!
    }

}