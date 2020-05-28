package com.ilizma.data.entity

import com.squareup.moshi.Json

data class DataRequest(
    val topRightCorner: TopRightCorner,
    val roverPosition: RoverPosition,
    val roverDirection: String,
    val movements: String
) {

    data class TopRightCorner(
        @Json(name = "x") val xCoordinate: Int,
        @Json(name = "y") val yCoordinate: Int
    )

    data class RoverPosition(
        @Json(name = "x") val xCoordinate: Int,
        @Json(name = "y") val yCoordinate: Int
    )
}