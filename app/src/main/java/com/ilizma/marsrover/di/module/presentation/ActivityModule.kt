package com.ilizma.marsrover.di.module.presentation

import com.ilizma.marsrover.di.scope.PerView
import com.ilizma.presentation.ui.customview.BaseCrashActivity
import com.ilizma.presentation.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @PerView
    @ContributesAndroidInjector
    abstract fun baseCrash(): BaseCrashActivity

    @PerView
    @ContributesAndroidInjector
    abstract fun main(): MainActivity

}