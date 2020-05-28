package com.ilizma.roverlib.internal

import com.ilizma.roverlib.Rover
import com.ilizma.roverlib.base.Failure
import com.ilizma.roverlib.entity.DataJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

private const val NORTH = "N"
private const val EAST = "E"
private const val SOUTH = "S"
private const val WEST = "W"
private const val LEFT = "L"
private const val RIGHT = "R"
private const val MOVE = "M"

internal class InternalRover : Rover {

    override fun move(json: String): String {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        try {
            val data = moshi.adapter(DataJson::class.java).fromJson(json)
            data?.let { safeData ->
                val topRightCornerX = safeData.topRightCorner.xCoordinate
                val topRightCornerY = safeData.topRightCorner.yCoordinate
                var roverPositionX = safeData.roverPosition.xCoordinate
                var roverPositionY = safeData.roverPosition.yCoordinate
                var direction = safeData.roverDirection
                val movements = safeData.movements.toList().map { it.toString() }

                movements.forEach { movement ->
                    when (movement) {
                        LEFT -> direction = spinLeft(direction)
                        RIGHT -> direction = spinRight(direction)
                        MOVE -> when (direction) {
                            NORTH -> if (roverPositionY < topRightCornerY) roverPositionY++
                            EAST -> if (roverPositionX < topRightCornerX) roverPositionX++
                            SOUTH -> if (roverPositionY > 0) roverPositionY--
                            WEST -> if (roverPositionX > 0) roverPositionX--
                        }
                        else -> throw Failure.IncorrectMovement("Incorrect movement, only L R M accepted")
                    }
                }
                return "$roverPositionX $roverPositionY $direction"
            } ?: throw Failure.NoData("No data entered")
        } catch (e: Exception) {
            throw Failure.ParseFailed("${DataJson::class.java.simpleName} parse failed: $e")
        }
    }

    private fun spinLeft(direction: String): String {
        return when (direction) {
            NORTH -> WEST
            EAST -> NORTH
            SOUTH -> EAST
            WEST -> SOUTH
            else -> throw Failure.IncorrectDirection("Incorrect direction, only N E S W accepted")
        }
    }

    private fun spinRight(direction: String): String {
        return when (direction) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
            else -> throw Failure.IncorrectDirection("Incorrect direction, only N E S W accepted")
        }
    }

}