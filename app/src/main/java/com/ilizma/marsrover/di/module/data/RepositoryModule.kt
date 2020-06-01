package com.ilizma.marsrover.di.module.data

import com.ilizma.data.repository.RepositoryImpl
import com.ilizma.domain.repository.Repository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun repository(repositoryImpl: RepositoryImpl): Repository

}