package com.ilizma.roverlib

import com.ilizma.roverlib.internal.InternalRover

interface Rover {

    fun move(json: String): String

    class Builder {
        fun build(): Rover {
            return InternalRover()
        }
    }

}