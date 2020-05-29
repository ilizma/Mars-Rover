package com.ilizma.marsrover.di.module.rover

import com.ilizma.roverlib.Rover
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoverModule {

    @Provides
    @Singleton
    fun rover(): Rover = Rover.Builder().build()

}