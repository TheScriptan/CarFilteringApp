package com.askominas.carfilteringapp.di

import androidx.lifecycle.ViewModelProvider
import com.askominas.carfilteringapp.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
