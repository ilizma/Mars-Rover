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

package com.ilizma.roverlib.entity

import com.squareup.moshi.Json

/**
 * internal DataJson class
 *
 * @property topRightCorner the TopRightCorner
 * @property roverPosition the RoverPosition
 * @property roverDirection the roverDirection String
 * @property movements the movements String
 */
internal data class DataJson(
    val topRightCorner: TopRightCorner,
    val roverPosition: RoverPosition,
    val roverDirection: String,
    val movements: String
) {

    /**
     * internal TopRightCorner class
     *
     * @property xCoordinate the x Int
     * @property yCoordinate the y Int
     */
    internal data class TopRightCorner(
        @Json(name = "x") val xCoordinate: Int,
        @Json(name = "y") val yCoordinate: Int
    )

    /**
     * internal RoverPosition class
     *
     * @property xCoordinate the x Int
     * @property yCoordinate the y Int
     */
    internal data class RoverPosition(
        @Json(name = "x") val xCoordinate: Int,
        @Json(name = "y") val yCoordinate: Int
    )
}