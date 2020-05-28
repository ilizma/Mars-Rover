package com.ilizma.roverlib.factory

import com.ilizma.roverlib.entity.DataJson

class DataJsonFactory {

    companion object {
        fun providesDataJson(
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