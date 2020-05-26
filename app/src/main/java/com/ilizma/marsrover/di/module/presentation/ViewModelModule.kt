package com.ilizma.marsrover.di.module.presentation

import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.ui.base.BaseViewModel
import com.ilizma.presentation.ui.main.MainViewModel
import com.ilizma.marsrover.di.viewmodel.ViewModelFactory
import com.ilizma.marsrover.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun factory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun main(vm: MainViewModel): BaseViewModel

}
