package com.ilizma.marsrover.di.module.data

import com.ilizma.data.entity.DataRequest
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class JsonModule {

    @Provides
    @Singleton
    fun moshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun jsonAdapter(): JsonAdapter<DataRequest> = moshi().adapter(DataRequest::class.java)

}