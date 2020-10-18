package com.askominas.carfilteringapp.di

import android.content.Context
import com.patloew.rxlocation.RxLocation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    companion object {
        @Singleton
        @Provides
        fun provideRxLocation(context: Context): RxLocation {
            return RxLocation(context)
        }
    }
}
