package com.ilizma.data.repository.datasources

import com.ilizma.data.entity.DataRequest
import com.ilizma.domain.base.Failure
import com.ilizma.roverlib.Rover
import com.ilizma.roverlib.base.IncorrectDirection
import com.ilizma.roverlib.base.IncorrectMovement
import com.squareup.moshi.JsonAdapter
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    val jsonAdapter: JsonAdapter<DataRequest>,
    val rover: Rover
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
        val dataRequestJson = jsonAdapter.toJson(dataRequest)
        return Single.create { emitter ->
            try {
                val result = rover.move(dataRequestJson)
                emitter.onSuccess(result)
            } catch (e: Throwable) {
                when (e) {
                    is IncorrectMovement -> emitter.tryOnError(Failure.IncorrectMovement(e.message!!))
                    is IncorrectDirection -> emitter.tryOnError(Failure.IncorrectDirection(e.message!!))
                    else -> emitter.tryOnError(e)
                }
            }
        }
    }

}