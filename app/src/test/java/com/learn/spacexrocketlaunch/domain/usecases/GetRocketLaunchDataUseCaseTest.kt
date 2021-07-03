package com.learn.spacexrocketlaunch.domain.usecases

import com.learn.spacexrocketlaunch.data.repository.RocketLaunchDataRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class GetRocketLaunchDataUseCaseTest {

    private val repository: RocketLaunchDataRepository = mock()
    lateinit var useCase: GetRocketLaunchDataUseCase

    @Before
    fun setUp() {
        useCase = GetRocketLaunchDataUseCase(repository)
    }

    @Test
    fun `should call getRocketLaunchDataFromApi and filter result `() {
        //Given
        whenever(repository.getRocketLaunchData()).thenReturn(Observable.just(listOf()))

        //When
        repository.getRocketLaunchData()

        //Then
        assertNotNull(repository.getRocketLaunchData())
        verify(repository, times(2)).getRocketLaunchData()
    }

}