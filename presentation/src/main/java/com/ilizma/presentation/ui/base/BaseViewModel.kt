package com.ilizma.presentation.ui.base

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilizma.domain.base.Failure
import com.ilizma.presentation.R
import dagger.Lazy
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    abstract var resources: Lazy<Resources>

    private var _ldLoading: MutableLiveData<Boolean> = MutableLiveData()
    val ldLoading: LiveData<Boolean> = _ldLoading

    private var _ldFailure: MutableLiveData<Failure> = MutableLiveData()
    val ldFailure: LiveData<Failure> = _ldFailure

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun loading(visible: Boolean) {
        _ldLoading.value = visible
    }

    protected fun handleFailureFromThrowable(throwable: Throwable, retryAction: () -> Unit) {
        val failure = getFailure(throwable, retryAction)
        _ldFailure.value = failure
    }

    private fun getFailure(throwable: Throwable, retryAction: () -> Unit): Failure {
        val failure = throwable as? Failure ?: Failure.Error(
            throwable.message?.let { safeMessage ->
                if (safeMessage.isNotEmpty()) {
                    safeMessage
                } else {
                    resources.get().getString(R.string.unknown_error)
                }
            } ?: resources.get().getString(R.string.unknown_error)
        )
        failure.retryAction = retryAction
        return failure
    }

}