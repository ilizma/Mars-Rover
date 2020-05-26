package com.ilizma.domain.usecase.rover

import com.ilizma.domain.repository.Repository
import com.ilizma.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class SendDataUseCase @Inject constructor(
    private val repository: Repository
) : SingleUseCase<String, Unit> {

    override fun invoke(params: Unit): Single<String> =
        repository.sendData()

}