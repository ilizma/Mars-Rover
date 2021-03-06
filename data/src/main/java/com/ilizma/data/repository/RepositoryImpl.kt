package com.ilizma.data.repository

import com.ilizma.data.repository.datasources.RemoteDataSource
import com.ilizma.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun sendData(
        topRightCornerXCoordinate: Int,
        topRightCornerYCoordinate: Int,
        roverPositionXCoordinate: Int,
        roverPositionYCoordinate: Int,
        roverDirection: String,
        roverMovements: String
    ): Single<String> =
        remoteDataSource.sendData(
            topRightCornerXCoordinate = topRightCornerXCoordinate,
            topRightCornerYCoordinate = topRightCornerYCoordinate,
            roverPositionXCoordinate = roverPositionXCoordinate,
            roverPositionYCoordinate = roverPositionYCoordinate,
            roverDirection = roverDirection,
            roverMovements = roverMovements
        )

}