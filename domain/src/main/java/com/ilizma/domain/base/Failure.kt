package com.ilizma.domain.base

sealed class Failure(message: String) : Throwable(message) {

    class IncorrectMovement(message: String) : Failure(message)

    class IncorrectDirection(message: String) : Failure(message)

}