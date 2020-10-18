package com.askominas.carfilteringapp.networking

import com.askominas.carfilteringapp.models.SparkCar
import retrofit2.Call
import retrofit2.http.GET


interface SparkCarApi {
    @GET("availablecars")
    fun listRepos(): Call<List<SparkCar>>
}
