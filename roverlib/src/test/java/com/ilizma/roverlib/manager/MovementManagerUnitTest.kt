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

package com.ilizma.roverlib.manager

import com.ilizma.roverlib.base.IncorrectDirection
import com.ilizma.roverlib.base.IncorrectMovement
import com.ilizma.roverlib.base.NoData
import com.ilizma.roverlib.base.ParseFailed
import com.ilizma.roverlib.entity.DataJson
import com.ilizma.roverlib.factory.DataJsonFactory.Companion.providesDataJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovementManagerUnitTest {

    private lateinit var movementManager: MovementManager
    private lateinit var moshi: Moshi
    private lateinit var jsonAdapter: JsonAdapter<DataJson>

    @Before
    fun setUp() {
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        jsonAdapter = moshi.adapter(DataJson::class.java)
        movementManager = MovementManager(jsonAdapter)
    }

    // region Success cases
    @Test
    fun `move should return a result string`() {
        val dataJson = providesDataJson()
        val json = jsonAdapter.toJson(dataJson)

        val result = movementManager.move(json)

        assert(result == "1 3 N")
    }
    // endregion

    // region Failure cases
    @Test(expected = ParseFailed::class)
    fun `move should return a parse failed Exception`() {
        val json = moshi.adapter(String::class.java).toJson("")

        movementManager.move(json)
    }

    @Test(expected = NoData::class)
    fun `move should return a no data entered Exception`() {
        val dataJson: DataJson? = null
        val json = jsonAdapter.toJson(dataJson)

        movementManager.move(json)
    }

    @Test(expected = IncorrectMovement::class)
    fun `move should return a incorrect movement Exception`() {
        val dataJson = providesDataJson(movements = "LMTL")
        val json = jsonAdapter.toJson(dataJson)

        movementManager.move(json)
    }

    @Test(expected = IncorrectDirection::class)
    fun `move should return a incorrect direction Exception`() {
        val dataJson = providesDataJson(roverDirection = "U")
        val json = jsonAdapter.toJson(dataJson)

        movementManager.move(json)
    }
    // endregion

}