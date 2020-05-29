package com.ilizma.data.repository.datasources

import com.ilizma.data.entity.DataRequest
import com.ilizma.data.entity.DataRequestFactory.Companion.providesDataRequest
import com.ilizma.data.extensions.assertGeneralsError
import com.ilizma.data.extensions.assertGeneralsSuccess
import com.ilizma.roverlib.Rover
import com.ilizma.roverlib.base.IncorrectDirection
import com.ilizma.roverlib.base.IncorrectMovement
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.whenever
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceUnitTest {

    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var moshi: Moshi

    @Mock
    private lateinit var rover: Rover

    @Mock
    private lateinit var jsonAdapter: JsonAdapter<DataRequest>

    @Before
    fun setUp() {
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        jsonAdapter = moshi.adapter(DataRequest::class.java)
        remoteDataSource = RemoteDataSource(jsonAdapter, rover)
    }

    // region Success cases
    @Test
    fun `sendData should return sendData Single`() {
        val dataRequest = providesDataRequest()
        val json = jsonAdapter.toJson(dataRequest)
        val result = "1 3 N"
        whenever(rover.move(json))
            .doReturn(result)

        val testObserver = remoteDataSource.sendData(
            topRightCornerXCoordinate = dataRequest.topRightCorner.xCoordinate,
            topRightCornerYCoordinate = dataRequest.topRightCorner.yCoordinate,
            roverPositionXCoordinate = dataRequest.roverPosition.xCoordinate,
            roverPositionYCoordinate = dataRequest.roverPosition.yCoordinate,
            roverDirection = dataRequest.roverDirection,
            roverMovements = dataRequest.movements
        ).test()

        testObserver.assertGeneralsSuccess {
            it == result
        }
    }
    // endregion

    // region Failure cases
    @Test(expected = MockitoException::class)
    fun `sendData should return a incorrect movement Exception`() {
        val dataRequest = providesDataRequest(movements = "LMTL")
        val json = jsonAdapter.toJson(dataRequest)
        whenever(rover.move(json))
            .doThrow(IncorrectMovement("Incorrect movement, only L R M accepted"))

        val testObserver = remoteDataSource.sendData(
            topRightCornerXCoordinate = dataRequest.topRightCorner.xCoordinate,
            topRightCornerYCoordinate = dataRequest.topRightCorner.yCoordinate,
            roverPositionXCoordinate = dataRequest.roverPosition.xCoordinate,
            roverPositionYCoordinate = dataRequest.roverPosition.yCoordinate,
            roverDirection = dataRequest.roverDirection,
            roverMovements = dataRequest.movements
        ).test()

        testObserver.assertGeneralsError()
    }

    @Test(expected = MockitoException::class)
    fun `sendData should return a incorrect direction Exception`() {
        val dataRequest = providesDataRequest(roverDirection = "U")
        val json = jsonAdapter.toJson(dataRequest)
        whenever(rover.move(json))
            .doThrow(IncorrectDirection("Incorrect direction, only N E S W accepted"))

        val testObserver = remoteDataSource.sendData(
            topRightCornerXCoordinate = dataRequest.topRightCorner.xCoordinate,
            topRightCornerYCoordinate = dataRequest.topRightCorner.yCoordinate,
            roverPositionXCoordinate = dataRequest.roverPosition.xCoordinate,
            roverPositionYCoordinate = dataRequest.roverPosition.yCoordinate,
            roverDirection = dataRequest.roverDirection,
            roverMovements = dataRequest.movements
        ).test()

        testObserver.assertGeneralsError()
    }
    // endregion

}