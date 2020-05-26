package com.ilizma.presentation.ui.main

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilizma.domain.usecase.rover.SendDataUseCase
import com.ilizma.presentation.ui.base.BaseViewModel
import dagger.Lazy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val sendDataUseCase: SendDataUseCase
) : BaseViewModel() {

    @Inject
    override lateinit var resources: Lazy<Resources>

    private val _ldRoverData = MutableLiveData<String>()
    val ldRoverData: LiveData<String> = _ldRoverData

    fun sendData() {
        sendDataUseCase(Unit)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loading(true) }
            .doAfterTerminate { loading(false) }
            .subscribe({ login ->
                _ldRoverData.value = login
            }, { throwable ->
                //TODO handleFailure(throwable) { lists() }
            })
            .addTo(compositeDisposable)
    }

}