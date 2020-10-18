package com.askominas.carfilteringapp.di

import androidx.lifecycle.ViewModel
import com.askominas.carfilteringapp.carlist.viewmodels.CarListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CarListViewModel::class)
    internal abstract fun bindMyViewModel(viewModel: CarListViewModel): ViewModel
}
