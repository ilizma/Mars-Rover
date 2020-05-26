package com.ilizma.marsrover.di.module.presentation

import dagger.Module

@Module(
    includes = [
        ActivityModule::class,
        AppModule::class,
        FragmentModule::class,
        ViewModelModule::class
    ]
)
class PresentationModule