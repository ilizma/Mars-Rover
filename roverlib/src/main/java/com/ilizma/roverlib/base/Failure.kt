/*
 * Copyright 2020 Iosu Lizarraga Madinabeitia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ilizma.roverlib.base

/**
 * ParseFailed class
 *
 * @property message the message of the parseFailed error
 * @return a throwable
 */
class ParseFailed(message: String) : Throwable(message)

/**
 * NoData class
 *
 * @property message the message of the noData error
 * @return a throwable
 */
class NoData(message: String = "No data entered") : Throwable(message)

/**
 * IncorrectMovement class
 *
 * @property message the message of the incorrectMovement error
 * @return a throwable
 */
class IncorrectMovement(message: String = "Incorrect movement, only L R M accepted") :
    Throwable(message)

/**
 * IncorrectDirection class
 *
 * @property message the message of the incorrectDirection error
 * @return a throwable
 */
class IncorrectDirection(message: String = "Incorrect direction, only N E S W accepted") :
    Throwable(message)