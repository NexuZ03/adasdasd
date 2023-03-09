package com.example.myapplication.remoto

import com.example.myapplication.servicios.CategoriaService

object ApiUtil {
    //configuramos una constante con la direccion del servicio
    val API_URL="http://192.168.1.14:8989/api-autoservicio/"
    //llamamos ala funcion getCliente del RetrofitCLient
    val categoriaService: CategoriaService?
        get()=RetrofitClient.getClient(API_URL)?.create(CategoriaService::class.java)

}