package com.ilizma.domain.repository

import io.reactivex.Single

interface Repository {

    fun sendData(
        topRightCornerXCoordinate: Int,
        topRightCornerYCoordinate: Int,
        roverPositionXCoordinate: Int,
        roverPositionYCoordinate: Int,
        roverDirection: String,
        roverMovements: String
    ): Single<String>

}