package com.ilizma.domain.repository

import io.reactivex.Single

interface Repository {

    fun sendData(
        topRightCornerXCoordinate: String,
        topRightCornerYCoordinate: String,
        roverPositionXCoordinate: String,
        roverPositionYCoordinate: String,
        roverDirection: String,
        roverMovements: String
    ): Single<String>

}