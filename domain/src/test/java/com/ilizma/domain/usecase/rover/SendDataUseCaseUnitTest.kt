package com.ilizma.domain.usecase.rover

import com.ilizma.domain.extensions.*
import com.ilizma.domain.repository.Repository
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
class SendDataUseCaseUnitTest {

    private lateinit var sendDataUseCase: SendDataUseCase

    @Mock
    private lateinit var repository: Repository

    private lateinit var params: SendDataParams

    @Before
    fun setUp() {
        sendDataUseCase = SendDataUseCase(repository)
        params = SendDataParams(
            topRightCornerXCoordinate = anyInt(),
            topRightCornerYCoordinate = anyInt(),
            roverPositionXCoordinate = anyInt(),
            roverPositionYCoordinate = anyInt(),
            roverDirection = anyString(),
            roverMovements = anyString()
        )
    }

    @Test
    fun `invoke should return SendData Single`() {
        val result = "1 3 N"
        with(params) {
            whenever(
                repository.sendData(
                    topRightCornerXCoordinate = topRightCornerXCoordinate,
                    topRightCornerYCoordinate = topRightCornerYCoordinate,
                    roverPositionXCoordinate = roverPositionXCoordinate,
                    roverPositionYCoordinate = roverPositionYCoordinate,
                    roverDirection = roverDirection,
                    roverMovements = roverMovements
                )
            )
                .doReturn(getSingleSuccess(result))
        }

        val testObserver = sendDataUseCase(params).testAwait()

        testObserver.assertGeneralsSuccess {
            it == result
        }
    }

    @Test
    fun `invoke should emit an error`() {
        with(params) {
            whenever(
                repository.sendData(
                    topRightCornerXCoordinate = topRightCornerXCoordinate,
                    topRightCornerYCoordinate = topRightCornerYCoordinate,
                    roverPositionXCoordinate = roverPositionXCoordinate,
                    roverPositionYCoordinate = roverPositionYCoordinate,
                    roverDirection = roverDirection,
                    roverMovements = roverMovements
                )
            )
                .doReturn(getSingleError())
        }

        val testObserver = sendDataUseCase(params).testAwait()

        testObserver.assertGeneralsError()
    }

}