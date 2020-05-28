package com.ilizma.data.repository.datasources

import com.ilizma.data.extensions.assertGeneralsError
import com.ilizma.data.extensions.assertGeneralsSuccess
import com.ilizma.data.extensions.getSingleError
import com.ilizma.data.extensions.getSingleSuccess
import com.nhaarman.mockitokotlin2.whenever
import com.squareup.moshi.Moshi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceUnitTest {

    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var moshi: Moshi

    @Before
    fun setUp() {
        remoteDataSource = RemoteDataSource(moshi)
    }

    // region Success cases
    @Test
    fun `sendData should return sendData Single`() {
        val result = "1 3 N"
        //TODO
        /*whenever(
            rover.sendData(
                anyInt(),
                anyInt(),
                anyInt(),
                anyInt(),
                anyString(),
                anyString()
            )
        )
            .doReturn(getSingleSuccess(result))*/

        val testObserver = remoteDataSource.sendData(
            topRightCornerXCoordinate = anyInt(),
            topRightCornerYCoordinate = anyInt(),
            roverPositionXCoordinate = anyInt(),
            roverPositionYCoordinate = anyInt(),
            roverDirection = anyString(),
            roverMovements = anyString()
        ).test()

        testObserver.assertGeneralsSuccess {
            it == result
        }
    }
    // endregion

    // region Failure cases
    @Test
    fun `sendData should return Failure`() {
        //TODO
        /*whenever(
            rover.sendData(
                anyInt(),
                anyInt(),
                anyInt(),
                anyInt(),
                anyString(),
                anyString()
            )
        )
            .doReturn(getSingleError())*/

        val testObserver = remoteDataSource.sendData(
            topRightCornerXCoordinate = anyInt(),
            topRightCornerYCoordinate = anyInt(),
            roverPositionXCoordinate = anyInt(),
            roverPositionYCoordinate = anyInt(),
            roverDirection = anyString(),
            roverMovements = anyString()
        ).test()

        testObserver.assertGeneralsError()
    }
    // endregion

}