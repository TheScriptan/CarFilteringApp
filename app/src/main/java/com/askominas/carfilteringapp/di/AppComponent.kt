package com.askominas.carfilteringapp.di

import android.app.Application
import android.content.Context
import com.askominas.carfilteringapp.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    interface Factory {
        //fun create(@BindsInstance application: Application): AppComponent
        fun create(@BindsInstance context: Context): AppComponent
    }
}
