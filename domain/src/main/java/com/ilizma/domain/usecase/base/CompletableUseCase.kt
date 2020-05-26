package com.ilizma.domain.usecase.base

import io.reactivex.Completable

interface CompletableUseCase<in Params> {

    operator fun invoke(params: Params): Completable

}