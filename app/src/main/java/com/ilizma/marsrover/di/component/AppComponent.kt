package com.ilizma.marsrover.di.component

import com.ilizma.marsrover.application.MarsRoverApplication
import com.ilizma.marsrover.di.module.data.DataModule
import com.ilizma.marsrover.di.module.presentation.PresentationModule
import com.ilizma.marsrover.di.module.rover.RoverModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DataModule::class,
        RoverModule::class,
        PresentationModule::class
    ]
)
interface AppComponent : AndroidInjector<MarsRoverApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: MarsRoverApplication): Builder

        fun build(): AppComponent
    }

}