package com.example.myapplication.remoto

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    //creamos una variable para el Retrofit
    private var retrofit: Retrofit?=null
    //creamos una funcion para la conexion al Retrofit
    fun getClient(url:String?):Retrofit?{
        if(retrofit==null){
            retrofit=Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
        return retrofit
    }

}