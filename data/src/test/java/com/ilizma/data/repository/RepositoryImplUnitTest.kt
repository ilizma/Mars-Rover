package com.ilizma.data.repository

import com.ilizma.data.extensions.assertGeneralsError
import com.ilizma.data.extensions.assertGeneralsSuccess
import com.ilizma.data.extensions.getSingleError
import com.ilizma.data.extensions.getSingleSuccess
import com.ilizma.data.repository.datasources.RemoteDataSource
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryImplUnitTest {
    private lateinit var repositoryImpl: RepositoryImpl

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        repositoryImpl = RepositoryImpl(remoteDataSource)
    }

    // region Success cases
    @Test
    fun `sendData should return result string single`() {
        val result = "1 3 N"
        whenever(
            remoteDataSource.sendData(
                topRightCornerXCoordinate = anyInt(),
                topRightCornerYCoordinate = anyInt(),
                roverPositionXCoordinate = anyInt(),
                roverPositionYCoordinate = anyInt(),
                roverDirection = anyString(),
                roverMovements = anyString()
            )
        )
            .doReturn(getSingleSuccess(result))

        val testObserver = repositoryImpl.sendData(
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
        whenever(
            remoteDataSource.sendData(
                topRightCornerXCoordinate = anyInt(),
                topRightCornerYCoordinate = anyInt(),
                roverPositionXCoordinate = anyInt(),
                roverPositionYCoordinate = anyInt(),
                roverDirection = anyString(),
                roverMovements = anyString()
            )
        )
            .doReturn(getSingleError())

        val testObserver = repositoryImpl.sendData(
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