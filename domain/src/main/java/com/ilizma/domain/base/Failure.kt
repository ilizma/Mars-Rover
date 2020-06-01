package com.ilizma.domain.base

sealed class Failure(val msg: String, var retryAction: () -> Unit) : Throwable() {

    class Error(msg: String) : Failure(msg, {})

    class IncorrectMovement(msg: String) : Failure(msg, {})

    class IncorrectDirection(msg: String) : Failure(msg, {})

}