package com.ilizma.roverlib.internal

import com.ilizma.roverlib.base.IncorrectDirection
import com.ilizma.roverlib.base.IncorrectMovement
import com.ilizma.roverlib.base.NoData
import com.ilizma.roverlib.base.ParseFailed
import com.ilizma.roverlib.entity.DataJson
import com.ilizma.roverlib.factory.DataJsonFactory.Companion.providesDataJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class InternalRoverUnitTest {

    private lateinit var internalRover: InternalRover
    private lateinit var moshi: Moshi

    @Before
    fun setUp() {
        internalRover = InternalRover()
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    // region Success cases
    @Test
    fun `move should return a result string`() {
        val dataJson = providesDataJson()
        val json = moshi.adapter(DataJson::class.java).toJson(dataJson)

        val result = internalRover.move(json)

        assert(result == "1 3 N")
    }
    // endregion

    // region Failure cases
    @Test(expected = ParseFailed::class)
    fun `move should return a parse failed Exception`() {
        val json = moshi.adapter(String::class.java).toJson("")

        internalRover.move(json)
    }

    @Test(expected = NoData::class)
    fun `move should return a no data entered Exception`() {
        val dataJson: DataJson? = null
        val json = moshi.adapter(DataJson::class.java).toJson(dataJson)

        internalRover.move(json)
    }

    @Test(expected = IncorrectMovement::class)
    fun `move should return a incorrect movement Exception`() {
        val dataJson = providesDataJson(movements = "LMTL")
        val json = moshi.adapter(DataJson::class.java).toJson(dataJson)

        internalRover.move(json)
    }

    @Test(expected = IncorrectDirection::class)
    fun `move should return a incorrect direction Exception`() {
        val dataJson = providesDataJson(roverDirection = "U")
        val json = moshi.adapter(DataJson::class.java).toJson(dataJson)

        internalRover.move(json)
    }
    // endregion

}