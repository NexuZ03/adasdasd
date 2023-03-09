package com.example.apptiendita.utilidad

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.ViewGroup
import android.widget.*

class Util {

    //creamos una variable para la alerta
    private var dialogo:AlertDialog.Builder?=null

    fun MensajeToast(context: Context?, men:String){
        Toast.makeText(context,men,Toast.LENGTH_LONG).show()
    }

    fun Limpiar (viewGroup: ViewGroup){
        var i=0
        val count=viewGroup.childCount
        while(i<count){
            val view=viewGroup.getChildAt(i)
            if(view is EditText){
                view.setText("")
            }
            if(view is RadioGroup){
                (view.getChildAt(0) as RadioButton).isChecked=false
            }
            //limpiando el spinner
            if(view is Spinner){
                view.setSelection(0)
            }
            //limpiar checkbox
            if(view is CheckBox){
                view.isChecked=false
            }
            if(view is ViewGroup && view.childCount>0){
                Limpiar(view as ViewGroup)
            }
            i++
        }

    }
    //funcion que pemrita salir del sistema
    fun SalirSistema(context: Context){
        dialogo= AlertDialog.Builder(context)
        dialogo!!.setTitle("Saliendo del Sistema")
        dialogo!!.setMessage("¿Estas seguro que quieres salir del sistema?")
        dialogo!!.setCancelable(false)
        dialogo!!.setPositiveButton("Si"){
                dialogo,which->(context as Activity).finish()
        }
        dialogo!!.setNegativeButton("No"){
                dialogo,which->
        }
        dialogo!!.show()
    }
}