# roverlib library

This library makes movements on Rover Mars and return the new position.

## How to use

Add this snippet in your code to get the instance of Rover 
```kotlin
val rover = Rover().build()
```

Use move(json: String) function passing a json with this format
```json
{
    "topRightCorner": {
         "x": 5,
         "y": 5
     },
     "roverPosition": {
         "x": 1,
         "y": 2
     },
     "roverDirection": "N",
     "movements": "LMLMLMLMM"
}
```
to move the rover on the plateau,
```kotlin
val result = rover.move(json)
```
and get an string with the new position output with this format
```kotlin
result == "1 3 N"
```

## License

The library is licensed under the [Apache License 2.0.](http://www.apache.org/licenses/LICENSE-2.0)