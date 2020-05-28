package com.ilizma.roverlib.base

sealed class Failure(message: String) : Throwable(message) {

    class ParseFailed(message: String) : Failure(message)

    class NoData(message: String) : Failure(message)

    class IncorrectMovement(message: String) : Failure(message)

    class IncorrectDirection(message: String) : Failure(message)

}