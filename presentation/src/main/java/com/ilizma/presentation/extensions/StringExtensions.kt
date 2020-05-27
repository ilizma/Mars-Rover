package com.ilizma.presentation.extensions

import java.util.regex.Pattern

private const val NORTH = "N"
private const val EAST = "E"
private const val SOUTH = "S"
private const val WEST = "W"
private const val LEFT = "L"
private const val RIGHT = "R"
private const val MOVE = "M"

fun String.isACardinalPoint() =
    this.length == 1 && (this == NORTH || this == EAST || this == SOUTH || this == WEST)

fun String.isAMovement() = Pattern.matches(".*[$LEFT$RIGHT$MOVE]", this)