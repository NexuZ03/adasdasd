package com.example.myapplication.servicios

import com.example.myapplication.clases.Categoria
import retrofit2.Call
import retrofit2.http.*


interface CategoriaService {
    //creamos los metodos para acceder al servicio web
    @GET("categoria")
    fun MostrarCategoria(): Call<List<Categoria>>
    @GET("categoria/custom")
    fun MostrarCategoriaPersonalizada(): Call<List<Categoria>>
    @POST("categoria")
    fun RegistrarCategoria(@Body c:Categoria?):Call<List<Categoria>>
    @PUT("categoria/{id}")
    fun ActualizarCategoria(@Path("id") id:Long,@Body c:Categoria?):Call<List<Categoria>>
    @DELETE("categoria/{id}")
    fun EliminarCategoria(@Path("id") id:Long):Call<List<Categoria>>

}