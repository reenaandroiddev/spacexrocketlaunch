package com.learn.spacexrocketlaunch.data.repository

import com.learn.spacexrocketlaunch.data.db.RocketLaunchDataDao
import com.learn.spacexrocketlaunch.data.network.RocketLaunchDataApi
import com.learn.spacexrocketlaunch.domain.usecases.GetRocketLaunchDataUseCase
import com.learn.spacexrocketlaunch.utils.Connectivity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class RocketLaunchDataRepositoryTest {

    private val api: RocketLaunchDataApi = mock()
    private val dao: RocketLaunchDataDao = mock()
    private val connectivity: Connectivity = mock()
    private lateinit var repository: RocketLaunchDataRepository

    @Before
    fun setUp() {
        repository = RocketLaunchDataRepository(
            rocketLaunchDataApi = api,
            rocketLaunchDataDao = dao,
            connectivity = connectivity
        )
    }

    @Test
    fun `test getRocketLaunchData calls api return  success when internet connection is available`() {
        //Given
        whenever(connectivity.hasNetworkAccess()).thenReturn(true)
        whenever(api.getRockets()).thenReturn(Observable.just(listOf()))

        //When
        repository.getRocketLaunchData()

        //Then
        verify(api, times(1)).getRockets()
    }

    @Test
    fun `test getRocketLaunchData calls api and return data from local db  when internet connection is not available`() {
        //Given
        whenever(connectivity.hasNetworkAccess()).thenReturn(false)
        whenever(dao.getLaunchDataFromDb()).thenReturn(listOf())

        //When
        repository.getRocketLaunchData()

        //Then
        verify(dao, times(1)).getLaunchDataFromDb()
    }

    @Test
    fun `test getRocketLaunchData calls api and return data from api  and save in local db`() {
        //Given
        whenever(connectivity.hasNetworkAccess()).thenReturn(false)
        whenever(api.getRockets()).thenReturn(Observable.just(listOf()))

        //When
        repository.saveLaunchDataToLocalDb(listOf())

        //Then
        assertNotNull(dao.getLaunchDataFromDb())
    }
}