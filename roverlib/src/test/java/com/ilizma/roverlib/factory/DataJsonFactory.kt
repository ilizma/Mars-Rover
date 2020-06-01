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

package com.ilizma.roverlib.factory

import com.ilizma.roverlib.entity.DataJson

internal class DataJsonFactory {

    companion object {
        internal fun providesDataJson(
            topRightCorner: DataJson.TopRightCorner = providesTopRightCorner(),
            roverPosition: DataJson.RoverPosition = providesRoverPosition(),
            roverDirection: String = "N",
            movements: String = "LMLMLMLMM"
        ) = DataJson(
            topRightCorner = topRightCorner,
            roverPosition = roverPosition,
            roverDirection = roverDirection,
            movements = movements
        )

        private fun providesTopRightCorner(
            xCoordinate: Int = 5,
            yCoordinate: Int = 5
        ) = DataJson.TopRightCorner(
            xCoordinate = xCoordinate,
            yCoordinate = yCoordinate
        )

        private fun providesRoverPosition(
            xCoordinate: Int = 1,
            yCoordinate: Int = 2
        ) = DataJson.RoverPosition(
            xCoordinate = xCoordinate,
            yCoordinate = yCoordinate
        )
    }

}