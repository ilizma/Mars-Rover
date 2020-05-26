package com.ilizma.data.repository

import com.ilizma.data.repository.datasources.RemoteDataSource
import com.ilizma.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun sendData(): Single<String> =
        remoteDataSource.sendData()

}