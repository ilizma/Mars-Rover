package com.ilizma.marsrover.di.module.presentation

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.ilizma.marsrover.application.MarsRoverApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(application: MarsRoverApplication): Application = application

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun resources(application: Application): Resources = application.resources

}