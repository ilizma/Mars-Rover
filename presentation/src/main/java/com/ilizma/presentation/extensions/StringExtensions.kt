package com.ilizma.presentation.extensions

private const val NORTH = "N"
private const val EAST = "E"
private const val SOUTH = "S"
private const val WEST = "W"
private const val LEFT = "L"
private const val RIGHT = "R"
private const val MOVE = "M"

fun String.isACardinalPoint() =
    this.length == 1 && (this.equals(NORTH, ignoreCase = true)
            || this.equals(EAST, ignoreCase = true)
            || this.equals(SOUTH, ignoreCase = true)
            || this.equals(WEST, ignoreCase = true))

fun String.isAMovement() =
    this.length == 1 && (this.contains(LEFT, ignoreCase = true)
            || this.contains(RIGHT, ignoreCase = true)
            || this.contains(MOVE, ignoreCase = true))