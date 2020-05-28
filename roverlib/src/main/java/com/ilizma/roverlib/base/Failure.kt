package com.ilizma.roverlib.base

class ParseFailed(message: String) : Throwable(message)

class NoData(message: String) : Throwable(message)

class IncorrectMovement(message: String) : Throwable(message)

class IncorrectDirection(message: String) : Throwable(message)