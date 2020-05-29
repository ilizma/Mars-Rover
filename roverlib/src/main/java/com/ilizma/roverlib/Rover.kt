package com.ilizma.roverlib

import com.ilizma.roverlib.entity.DataJson
import com.ilizma.roverlib.manager.MovementManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Rover {

    private lateinit var movementManager: MovementManager

    fun move(json: String): String = movementManager.move(json)

    fun build(): Rover {
        val rover = Rover()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter = moshi.adapter(DataJson::class.java)
        rover.movementManager = MovementManager(jsonAdapter)
        return rover
    }

}