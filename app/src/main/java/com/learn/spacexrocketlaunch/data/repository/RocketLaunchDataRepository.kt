package com.learn.spacexrocketlaunch.data.repository

import com.learn.spacexrocketlaunch.data.db.RocketLaunchDataDao
import com.learn.spacexrocketlaunch.data.mapper.RocketLaunchDataMapper
import com.learn.spacexrocketlaunch.data.model.RocketLaunchDataModel
import com.learn.spacexrocketlaunch.data.network.RocketLaunchDataApi
import com.learn.spacexrocketlaunch.utils.Connectivity
import io.reactivex.Observable
import javax.inject.Inject

class RocketLaunchDataRepository @Inject constructor(
    private val rocketLaunchDataApi: RocketLaunchDataApi,
    private val rocketLaunchDataDao: RocketLaunchDataDao,
    private val connectivity: Connectivity
) {
    fun getRocketLaunchData(): Observable<List<RocketLaunchDataModel>> = when {
        connectivity.hasNetworkAccess() -> {
            getRocketLaunchDataFromApi()
        }
        else -> {
            Observable.just(rocketLaunchDataDao.getLaunchDataFromDb())
        }
    }

    fun saveLaunchDataToLocalDb(launchDataList: List<RocketLaunchDataModel>) {
        rocketLaunchDataDao.clearDb()
        launchDataList.forEach { rocketLaunchDataDao.saveLaunchData(it) }
    }

    private fun getRocketLaunchDataFromApi(): Observable<List<RocketLaunchDataModel>> {
        return rocketLaunchDataApi.getRockets().flatMap {
            Observable.fromIterable(it).filter { item -> item.active }.toList().toObservable()
        }.map { RocketLaunchDataMapper.map(it) }
    }
}