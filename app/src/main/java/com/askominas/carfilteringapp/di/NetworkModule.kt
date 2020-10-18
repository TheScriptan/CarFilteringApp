package com.askominas.carfilteringapp.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://development.espark.lt/api/mobile/public/"

@Module
abstract class NetworkModule {

    companion object {

        @Singleton
        @Provides
        fun provideRetrofit(): Retrofit {

            val gson = GsonBuilder().create()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()
        }
    }
}
