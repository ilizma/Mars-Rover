package com.ilizma.data.repository.datasources

import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {

    fun sendData(): Single<String> {
        return Single.create {
            //TODO get DATA
        }
    }

}