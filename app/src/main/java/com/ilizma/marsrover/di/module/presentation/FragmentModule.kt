package com.ilizma.marsrover.di.module.presentation

import com.ilizma.presentation.ui.main.MainFragment
import com.ilizma.marsrover.di.scope.PerView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @PerView
    @ContributesAndroidInjector
    abstract fun main(): MainFragment

}
