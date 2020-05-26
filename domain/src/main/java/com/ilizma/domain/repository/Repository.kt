package com.ilizma.domain.repository

import io.reactivex.Single

interface Repository {

    fun sendData(): Single<String>

}