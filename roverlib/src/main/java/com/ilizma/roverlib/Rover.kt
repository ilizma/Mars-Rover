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

package com.ilizma.roverlib

import com.ilizma.roverlib.entity.DataJson
import com.ilizma.roverlib.manager.MovementManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Rover class
 */
class Rover {

    private lateinit var movementManager: MovementManager

    /**
     * calls movementManager.move function that contains the logic to move Rover on the plateau
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
    fun move(json: String): String = movementManager.move(json)

    /**
     * builds Rover class with the manager instances
     *
     * @return the Rover class instance
     */
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