package com.ilizma.roverlib.internal

import com.ilizma.roverlib.Rover
import com.ilizma.roverlib.base.IncorrectDirection
import com.ilizma.roverlib.base.IncorrectMovement
import com.ilizma.roverlib.base.NoData
import com.ilizma.roverlib.base.ParseFailed
import com.ilizma.roverlib.entity.DataJson
import com.squareup.moshi.JsonAdapter

private const val NORTH = "N"
private const val EAST = "E"
private const val SOUTH = "S"
private const val WEST = "W"
private const val LEFT = "L"
private const val RIGHT = "R"
private const val MOVE = "M"

internal class InternalRover(private val jsonAdapter: JsonAdapter<DataJson>) : Rover {

    private var topRightCornerX: Int = 0
    private var topRightCornerY: Int = 0
    private var roverPositionX: Int = 0
    private var roverPositionY: Int = 0
    private lateinit var direction: String
    private lateinit var movements: List<String>

    override fun move(json: String): String {
        try {
            val data = jsonAdapter.fromJson(json)
            data?.let { safeData ->
                topRightCornerX = safeData.topRightCorner.xCoordinate
                topRightCornerY = safeData.topRightCorner.yCoordinate
                roverPositionX = safeData.roverPosition.xCoordinate
                roverPositionY = safeData.roverPosition.yCoordinate
                direction = safeData.roverDirection
                movements = safeData.movements.toList().map { it.toString() }

                movements.forEach { movement ->
                    when (movement) {
                        LEFT -> spinLeft()
                        RIGHT -> spinRight()
                        MOVE -> moveOneGrid()
                        else -> throw IncorrectMovement()
                    }
                }
                return "$roverPositionX $roverPositionY $direction"
            } ?: throw NoData()
        } catch (e: Exception) {
            throw ParseFailed("${DataJson::class.java.simpleName} parse failed: $e")
        }
    }

    private fun spinLeft() {
        direction = when (direction) {
            NORTH -> WEST
            EAST -> NORTH
            SOUTH -> EAST
            WEST -> SOUTH
            else -> throw IncorrectDirection()
        }
    }

    private fun spinRight() {
        direction = when (direction) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
            else -> throw IncorrectDirection()
        }
    }

    private fun moveOneGrid() {
        when (direction) {
            NORTH -> if (roverPositionY < topRightCornerY) roverPositionY++
            EAST -> if (roverPositionX < topRightCornerX) roverPositionX++
            SOUTH -> if (roverPositionY > 0) roverPositionY--
            WEST -> if (roverPositionX > 0) roverPositionX--
        }
    }

}