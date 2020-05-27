package com.ilizma.data.repository.datasources

import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {

    fun sendData(
        topRightCornerXCoordinate: String,
        topRightCornerYCoordinate: String,
        roverPositionXCoordinate: String,
        roverPositionYCoordinate: String,
        roverDirection: String,
        roverMovements: String
    ): Single<String> {
        return Single.create {
            //TODO get DATA
        }
    }

}