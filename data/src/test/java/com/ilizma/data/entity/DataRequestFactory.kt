package com.ilizma.data.entity

class DataRequestFactory {

    companion object {
        fun providesDataRequest(
            topRightCorner: DataRequest.TopRightCorner = providesTopRightCorner(),
            roverPosition: DataRequest.RoverPosition = providesRoverPosition(),
            roverDirection: String = "N",
            movements: String = "LMLMLMLMM"
        ) = DataRequest(
            topRightCorner = topRightCorner,
            roverPosition = roverPosition,
            roverDirection = roverDirection,
            movements = movements
        )

        private fun providesTopRightCorner(
            xCoordinate: Int = 5,
            yCoordinate: Int = 5
        ) = DataRequest.TopRightCorner(
            xCoordinate = xCoordinate,
            yCoordinate = yCoordinate
        )

        private fun providesRoverPosition(
            xCoordinate: Int = 1,
            yCoordinate: Int = 2
        ) = DataRequest.RoverPosition(
            xCoordinate = xCoordinate,
            yCoordinate = yCoordinate
        )
    }

}