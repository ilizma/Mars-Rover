package com.ilizma.data.extensions

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber

fun <T : Any> TestSubscriber<T>.assertGeneralsSuccess(asserts: (T) -> Boolean = { true }) {
    assertComplete()
    assertValueCount(1)
    assertNoErrors()
    assertValue {
        asserts(it)
    }
}

fun <T : Any> TestObserver<T>.assertGeneralsSuccess(asserts: (T) -> Boolean = { true }) {
    assertComplete()
    assertValueCount(1)
    assertNoErrors()
    assertValue {
        asserts(it)
    }
}

fun <T : Any> TestObserver<T>.assertGeneralsObservableSuccess(
    count: Int = 1,
    asserts: (T) -> Boolean = { true }
) {
    assertValueCount(count)
    assertNoErrors()
    assertValue {
        asserts(it)
    }
}

fun <T : Any> TestObserver<T>.assertGeneralsCompletableSuccess() {
    assertComplete()
    assertNoErrors()
}

fun <T : Any> TestObserver<T>.assertGeneralsError(asserts: (Throwable) -> Boolean = { true }) {
    assertValueCount(0)
    assertError {
        asserts(it)
    }
}

fun <T> getSingleSuccess(value: T): Single<T> = Single.just(value)

fun <T> getSingleError(value: Throwable): Single<T> = Single.error(value)

fun getCompletableComplete(): Completable = Completable.complete()

fun getCompletableError(value: Throwable): Completable = Completable.error(value)