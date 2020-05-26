package com.ilizma.marsrover.di.module.data

import dagger.Module

@Module(
    includes = [
        RepositoryModule::class
    ]
)
class DataModule