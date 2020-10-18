package com.askominas.carfilteringapp.di

import com.askominas.carfilteringapp.carlist.ui.CarListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCarListFragment(): CarListFragment
}