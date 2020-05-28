package com.ilizma.roverlib.entity

import com.squareup.moshi.Json

internal data class DataJson(
    val topRightCorner: TopRightCorner,
    val roverPosition: RoverPosition,
    val roverDirection: String,
    val movements: String
) {

    internal data class TopRightCorner(
        @Json(name = "x") val xCoordinate: Int,
        @Json(name = "y") val yCoordinate: Int
    )

    internal data class RoverPosition(
        @Json(name = "x") val xCoordinate: Int,
        @Json(name = "y") val yCoordinate: Int
    )
}