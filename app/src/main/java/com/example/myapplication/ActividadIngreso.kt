package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.apptiendita.utilidad.Util

class ActividadIngreso : AppCompatActivity() {

    //creamos un objeto de clase util
    private val objutilidad= Util()
    //declaramos variables para el usuario y clave
    private var usu=""
    private var cla=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_ingreso)

        //creamos una constet
        val txtUsu=findViewById<EditText>(R.id.txtUsu)
        val txtCla=findViewById<EditText>(R.id.txtCla)
        val btnIngresar=findViewById<Button>(R.id.btnIngresar)
        val btnSalir=findViewById<Button>(R.id.btnSalir)

        //creamos una variable para el contexto
        val contexT=this


        //agregamos el evento para el boton
        btnIngresar.setOnClickListener {
            if(txtUsu.text.toString()==""){
                objutilidad.MensajeToast(this,"Ingrese")
                txtUsu.requestFocus()
            }else if(txtCla.text.toString()==""){
                objutilidad.MensajeToast(this,"Ingrese")
                txtCla.requestFocus()
            }else{
                usu=txtUsu.text.toString()
                cla=txtCla.text.toString()
                if(usu=="NexuZ03"&&cla=="123456") {
                    objutilidad.MensajeToast(this, "Bienvenido al sistema")
                    val formulario = Intent(this, ActivityPrincipal::class.java)
                    startActivity(formulario)
                    this.finish()
                }else{
                    objutilidad.MensajeToast(this, "Usuario o clave no valida")
                    objutilidad.Limpiar(findViewById<View>(R.id.frmIngreso) as ViewGroup)
                    txtUsu.requestFocus()
                }
            }
        }
        btnSalir.setOnClickListener {
            objutilidad.SalirSistema(this)
        }
    }
}