package com.ilizma.roverlib.internal

import com.ilizma.roverlib.base.Failure
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

    @Test
    fun `move should return a result string`() {
        val dataJson = providesDataJson()
        val json = moshi.adapter(DataJson::class.java).toJson(dataJson)

        val result = internalRover.move(json)

        assert(result == "1 3 N")
    }

    @Test(expected = Failure.ParseFailed::class)
    fun `move should return a parse failed Exception`() {
        val json = moshi.adapter(String::class.java).toJson("")

        internalRover.move(json)
    }

    @Test(expected = Failure.NoData::class)
    fun `move should return a no data entered Exception`() {
        val dataJson: DataJson? = null
        val json = moshi.adapter(DataJson::class.java).toJson(dataJson)

        internalRover.move(json)
    }

    @Test(expected = Failure.IncorrectMovement::class)
    fun `move should return a incorrect movement Exception`() {
        val dataJson = providesDataJson(movements = "LMTL")
        val json = moshi.adapter(DataJson::class.java).toJson(dataJson)

        internalRover.move(json)
    }

    @Test(expected = Failure.IncorrectDirection::class)
    fun `move should return a incorrect direction Exception`() {
        val dataJson = providesDataJson(roverDirection = "U")
        val json = moshi.adapter(DataJson::class.java).toJson(dataJson)

        internalRover.move(json)
    }

}