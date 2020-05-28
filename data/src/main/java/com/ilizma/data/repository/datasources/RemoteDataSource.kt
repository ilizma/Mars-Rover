package com.ilizma.data.repository.datasources

import androidx.annotation.VisibleForTesting
import com.ilizma.data.entity.DataRequest
import com.ilizma.domain.base.Failure
import com.ilizma.roverlib.Rover
import com.ilizma.roverlib.base.IncorrectDirection
import com.ilizma.roverlib.base.IncorrectMovement
import com.ilizma.roverlib.base.NoData
import com.ilizma.roverlib.base.ParseFailed
import com.squareup.moshi.Moshi
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    val moshi: Moshi,
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
        val dataRequestJson = convertToJson(dataRequest)
        return Single.create { emitter ->
            try {
                val result = rover.move(dataRequestJson)
                emitter.onSuccess(result)
            } catch (e: Throwable) {
                when (e) {
                    is ParseFailed -> emitter.tryOnError(Failure.ParseFailed(e.message!!))
                    is NoData -> emitter.tryOnError(Failure.NoData(e.message!!))
                    is IncorrectMovement -> emitter.tryOnError(Failure.IncorrectMovement(e.message!!))
                    is IncorrectDirection -> emitter.tryOnError(Failure.IncorrectDirection(e.message!!))
                    else -> emitter.tryOnError(e)
                }
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun convertToJson(dataRequest: DataRequest): String {
        return moshi.adapter(DataRequest::class.java).toJson(dataRequest)
    }

}