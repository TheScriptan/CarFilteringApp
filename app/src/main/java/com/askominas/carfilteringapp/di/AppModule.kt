package com.askominas.carfilteringapp.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    companion object{

        @Singleton
        @Provides
        fun someTestString():String{
            return "Hello World"
        }
    }
}