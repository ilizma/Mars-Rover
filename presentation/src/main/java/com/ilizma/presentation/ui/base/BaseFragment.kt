package com.ilizma.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.hideKeyboard
import com.ilizma.presentation.extensions.inflate
import com.ilizma.presentation.extensions.snackbar
import dagger.Lazy
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : DaggerFragment() {

    abstract var viewModelFactory: Lazy<ViewModelProvider.Factory>

    @setparam:LayoutRes
    abstract var fragmentLayout: Int

    private var snackbar: Snackbar? = null

    protected val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(fragmentLayout)

    override fun onPause() {
        dismissSnackbar()
        super.onPause()
    }

    override fun onDestroy() {
        hideKeyboard()
        dispose()
        super.onDestroy()
    }

    protected fun dismissSnackbar() {
        snackbar?.dismiss()
    }

    protected fun showDialog(fragment: DialogFragment) {
        dismissSnackbar()
        if (activity != null
            && requireActivity().isFinishing.not()
            && parentFragmentManager.isDestroyed.not()
            && parentFragmentManager.isStateSaved.not()
        ) {
            try {
                fragment.show(parentFragmentManager, fragment::class.simpleName)
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
        }
    }

    fun showSnackbarWithRes(
        @StringRes title: Int,
        @StringRes action: Int,
        length: Int = Snackbar.LENGTH_LONG,
        actionResult: () -> Unit = {}
    ) {
        var container = parentFragment?.view?.findViewById<View?>(R.id.parentContainer)
        container ?: run {
            container = activity?.findViewById(R.id.parentContainer)
        }
        snackbar = container?.snackbar(title, action, length, actionResult = actionResult)
    }

    fun showSnackbar(
        title: String,
        action: String,
        length: Int = Snackbar.LENGTH_LONG,
        actionResult: () -> Unit = {}
    ) {
        var container = parentFragment?.view?.findViewById<View?>(R.id.parentContainer)
        container ?: run {
            container = activity?.findViewById(R.id.parentContainer)
        }
        snackbar = container?.snackbar(title, action, length, actionResult = actionResult)
    }

    private fun dispose() {
        compositeDisposable.clear()
    }

}