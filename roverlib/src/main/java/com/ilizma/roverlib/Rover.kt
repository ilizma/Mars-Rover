package com.ilizma.roverlib

import com.ilizma.roverlib.entity.DataJson
import com.ilizma.roverlib.internal.InternalRover
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

interface Rover {

    fun move(json: String): String

    class Builder {
        fun build(): Rover {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val jsonAdapter = moshi.adapter(DataJson::class.java)
            return InternalRover(jsonAdapter)
        }
    }

}