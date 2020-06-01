package com.ilizma.presentation.extensions

import com.google.android.material.snackbar.Snackbar
import com.ilizma.domain.base.Failure
import com.ilizma.presentation.R
import com.ilizma.presentation.ui.base.BaseFragment

fun BaseFragment.handleFailure(
    failure: Failure
) {
    handleLengthFailure(failure)
}

fun BaseFragment.handleLengthFailure(
    failure: Failure,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    showSnackbar(
        failure.msg,
        getString(R.string.retry),
        length
    ) { failure.retryAction() }
}