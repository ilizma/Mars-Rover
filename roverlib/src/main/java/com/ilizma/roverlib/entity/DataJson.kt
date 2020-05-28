package com.ilizma.roverlib.entity

import androidx.annotation.VisibleForTesting
import com.squareup.moshi.Json

@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
data class DataJson(
    val topRightCorner: TopRightCorner,
    val roverPosition: RoverPosition,
    val roverDirection: String,
    val movements: String
) {

    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    data class TopRightCorner(
        @Json(name = "x") val xCoordinate: Int,
        @Json(name = "y") val yCoordinate: Int
    )

    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    data class RoverPosition(
        @Json(name = "x") val xCoordinate: Int,
        @Json(name = "y") val yCoordinate: Int
    )
}