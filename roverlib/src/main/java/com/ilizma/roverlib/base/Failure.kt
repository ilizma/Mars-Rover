package com.ilizma.roverlib.base

class ParseFailed(message: String) : Throwable(message)

class NoData(message: String = "No data entered") : Throwable(message)

class IncorrectMovement(message: String = "Incorrect movement, only L R M accepted") : Throwable(message)

class IncorrectDirection(message: String = "Incorrect direction, only N E S W accepted") : Throwable(message)