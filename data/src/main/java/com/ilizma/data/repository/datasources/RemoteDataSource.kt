package com.ilizma.data.repository.datasources

import com.ilizma.data.entity.DataRequest
import com.squareup.moshi.Moshi
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    val moshi: Moshi
) {

    fun sendData(
        topRightCornerXCoordinate: Int,
        topRightCornerYCoordinate: Int,
        roverPositionXCoordinate: Int,
        roverPositionYCoordinate: Int,
        roverDirection: String,
        roverMovements: String
    ): Single<String> {
        val dataRequest = DataRequest(
            DataRequest.TopRightCorner(
                topRightCornerXCoordinate,
                topRightCornerYCoordinate
            ),
            DataRequest.RoverPosition(
                roverPositionXCoordinate,
                roverPositionYCoordinate
            ),
            roverDirection,
            roverMovements
        )
        val dataRequestJson = moshi.adapter(DataRequest::class.java).toJson(dataRequest)
        return Single.create {

        }//TODO rover(dataRequestJson)
    }

}