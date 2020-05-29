# Mars Rover Iosu Test App

## Getting Setup with Android Studio

* [Download the latest Android Studio](https://developer.android.com/studio)
* Download the latest resources in Android SDK Manger
* Add the latest Kotlin plugin

## Building the Project

Opening the project should trigger a gradle sync.

## Running Tests

Run Data and Domain layer UnitTest right-clicking on its corresponding test package.

## Code Style Guide

* [Kotlin Code Conventions](https://kotlinlang.org/docs/reference/coding-conventions.html)
* [Android Kotlin Guides](https://android.github.io/kotlin-guides/style.html)

## GitFlow

A new feature needs a new branch from develop. 'git checkout -b feature/name-of-feature'

Do "squash and merge" to merge a branch in develop.

Do "merge" to merge develop to master.

* Add at the beginning of the commit description:

[WIP] work in progress

[FIX] a code fix

[FEAT] feature is done

## How it works

On the first screen, different text boxes are displayed, which must be completed with the text type indicated in the description.

When all the boxes are completed correctly, the send button is enabled and when you click on it, all data is sent to RoverLib.

RoverLib calculates the movement and returns the new position.

The new position is displayed in a dialog.