/*
 * Copyright 2020 Iosu Lizarraga Madinabeitia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ilizma.roverlib.manager

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

/**
 * internal MovementManager class
 *
 * @property jsonAdapter the jsonAdapter<DataJson> to get the DataJson object from a json
 */
internal class MovementManager(private val jsonAdapter: JsonAdapter<DataJson>) {

    private var topRightCornerX: Int = 0
    private var topRightCornerY: Int = 0
    private var roverPositionX: Int = 0
    private var roverPositionY: Int = 0
    private lateinit var direction: String
    private lateinit var movements: List<String>

    /**
     * moves Rover on the plateau
     *
     * Example of the input json and output String:
     * input: {
     *     topRightCorner: {
     *         x: 5,
     *         y: 5
     *     },
     *     roverPosition: {
     *         x: 1,
     *         y: 2
     *     },
     *     roverDirection: "N",
     *     movements: "LMLMLMLMM"
     * }
     * output: "1 3 N"
     *
     * @property json the json that contains the information of the topRightCorner, roverPosition,
     * roverDirection and the movements
     * @return the new position of Rover after do the movements
     */
    fun move(json: String): String {
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