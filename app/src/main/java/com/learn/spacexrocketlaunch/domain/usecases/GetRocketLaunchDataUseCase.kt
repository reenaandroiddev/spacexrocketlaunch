package com.learn.spacexrocketlaunch.domain.usecases

import com.learn.spacexrocketlaunch.data.model.RocketLaunchDataModel
import com.learn.spacexrocketlaunch.data.repository.RocketLaunchDataRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRocketLaunchDataUseCase @Inject constructor(private val repository: RocketLaunchDataRepository) {

    fun getRocketLaunchData(): Observable<List<RocketLaunchDataModel>> {
        return repository.getRocketLaunchData()
    }
}