package com.example.myapplication


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.apptiendita.utilidad.Util
import com.example.myapplication.adaptadores.AdaptadorCategoria
import com.example.myapplication.clases.Categoria
import com.example.myapplication.databinding.FragmentoCategoriaBinding
import com.example.myapplication.remoto.ApiUtil
import com.example.myapplication.servicios.CategoriaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FragmentoCategoria : Fragment() {
    //declaramos los controles
    private lateinit var txtNom:EditText
    private lateinit var chkEst:CheckBox
    private lateinit var lblCodCat:TextView
    private lateinit var btnRegistrar:Button
    private lateinit var btnActualizar:Button
    private lateinit var btnEliminar:Button
    private lateinit var lstCat:ListView

    //cremamos un objeto de la clase categoria
    private val objcategoria= Categoria()

    //creamos variables
    private var cod=0
    private var fila=-1
    private var nom=""
    private var est=false

    //llamamos al servicio
    private var categoriaService: CategoriaService?=null

    //creamos una lista de tipo categoria
    private var registrocategoria:List<Categoria>?=null

    //creamos un objeto de la clase Util
    private val objutilidad= Util()

    //creams una variable para actualizar el fragmento
    var ft: FragmentTransaction?=null


    private var _binding: FragmentoCategoriaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val raiz=inflater.inflate(R.layout.fragmento_categoria,container,false)
        //creamos los controles
        txtNom=raiz.findViewById(R.id.txtNom)
        chkEst=raiz.findViewById(R.id.chkEst)
        lblCodCat=raiz.findViewById(R.id.lblCodCat)
        btnRegistrar=raiz.findViewById(R.id.btnRegistrar)
        btnActualizar=raiz.findViewById(R.id.btnActualizar)
        btnEliminar=raiz.findViewById(R.id.btnEliminar)
        lstCat=raiz.findViewById(R.id.lstCat)

        //creamos el registro categoria
        registrocategoria=ArrayList()
        //implementamos el servicio
        categoriaService= ApiUtil.categoriaService
        //mostramos las categorias
        MostrarCategoria(raiz.context)

        //agregamos los eventos a los botones
        btnRegistrar.setOnClickListener {
            if(txtNom.getText().toString()==""){
                objutilidad.MensajeToast(context,"Ingrese el nombre")
                txtNom.requestFocus()
            }else{
                //capturando valores
                nom=txtNom.getText().toString()
                est=if(chkEst.isChecked){
                    true
                }else{
                    false
                }
                //enviando los valores a la clase
                objcategoria.nombre=nom
                objcategoria.estado=est
                //registramos los valores
                RegistrarCategoria(raiz.context,objcategoria)
                objutilidad.Limpiar(raiz.findViewById<View>(R.id.frmCategoria) as ViewGroup)
                val fcategoria= FragmentoCategoria()
                ft=fragmentManager?.beginTransaction()
                ft?.replace(R.id.contenedor,fcategoria,null)
                ft?.addToBackStack(null)
                ft?.commit()
            }
        }

        //para la seleccion de la lista
        lstCat.setOnItemClickListener(AdapterView.OnItemClickListener
        { parent, view, position, id ->
            fila=position
            lblCodCat.setText(""+(registrocategoria as ArrayList<Categoria>).get(fila).codigo)
            txtNom.setText(""+(registrocategoria as ArrayList<Categoria>).get(fila).nombre)
            if((registrocategoria as ArrayList<Categoria>).get(fila).estado){
                chkEst.setChecked(true)
            }else{
                chkEst.setChecked(false)
            }

        }
        )


        return raiz
    }



    //creamos una funcion para mostrar la categoria
    fun MostrarCategoria(context: Context?){
        val call= categoriaService!!.MostrarCategoriaPersonalizada()
        call!!.enqueue(object: Callback<List<Categoria>> {
            override fun onResponse(
                call: Call<List<Categoria>>,
                response: Response<List<Categoria>>
            ) {
                if(response.isSuccessful){
                    registrocategoria=response.body()
                    lstCat.adapter= AdaptadorCategoria(context,registrocategoria)
                }
            }

            override fun onFailure(call: Call<List<Categoria>>, t: Throwable) {
                Log.e("Error: ",t.message!!)
            }

        })
    }

    //creamos una funcion para registrar la categoria
    fun RegistrarCategoria(context: Context?, c:Categoria?){
        val call= categoriaService!!.RegistrarCategoria(c)
        call!!.enqueue(object: Callback<List<Categoria>>{
            override fun onResponse(
                call: Call<List<Categoria>>,
                response: Response<List<Categoria>>
            ) {
                if(response.isSuccessful){
                    objutilidad.MensajeToast(context,"Se registro la categoria")
                }
            }

            override fun onFailure(call: Call<List<Categoria>>, t: Throwable) {
                Log.e("Error: ",t.message!!)
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
