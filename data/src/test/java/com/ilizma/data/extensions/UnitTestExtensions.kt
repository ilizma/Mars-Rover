package com.ilizma.data.extensions

import io.reactivex.Single
import io.reactivex.observers.TestObserver

fun <T : Any> TestObserver<T>.assertGeneralsSuccess(asserts: (T) -> Boolean = { true }) {
    assertComplete()
    assertValueCount(1)
    assertNoErrors()
    assertValue {
        asserts(it)
    }
}

fun <T : Any> TestObserver<T>.assertGeneralsError(asserts: (Throwable) -> Boolean = { true }) {
    assertValueCount(0)
    assertError {
        asserts(it)
    }
}

fun <T> getSingleSuccess(value: T): Single<T> = Single.just(value)

fun <T> getSingleError(value: Throwable = Throwable()): Single<T> = Single.error(value)