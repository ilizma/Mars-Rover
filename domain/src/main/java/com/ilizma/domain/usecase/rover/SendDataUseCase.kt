package com.ilizma.domain.usecase.rover

import com.ilizma.domain.repository.Repository
import com.ilizma.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class SendDataUseCase @Inject constructor(
    private val repository: Repository
) : SingleUseCase<String, SendDataParams> {

    override fun invoke(params: SendDataParams): Single<String> =
        repository.sendData()

}

class SendDataParams(
    val topRightCornerXCoordinate: String,
    val topRightCornerYCoordinate: String,
    val roverPositionXCoordinate: String,
    val roverPositionYCoordinate: String,
    val roverDirection: String,
    val roverMovements: String
)