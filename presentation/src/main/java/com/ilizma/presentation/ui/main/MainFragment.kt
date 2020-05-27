package com.ilizma.presentation.ui.main

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.*
import com.ilizma.presentation.ui.base.BaseFragment
import com.jakewharton.rxbinding3.widget.textChanges
import dagger.Lazy
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function6
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : BaseFragment() {

    private lateinit var mainViewModel: MainViewModel

    @Inject
    override lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    override var fragmentLayout = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
        setUpUI()
        setUpViewModel()
    }

    private fun setUpToolBar() {
        (activity as MainActivity).setSupportActionBar(toolbar)
    }

    private fun setUpUI() {
        val topRightCornerXCoordinateObservable =
            topRightCornerXCoordinateEtv.textChanges()
                .skipInitialValue()
                .map(::trimText)
                .map(::onTopRightCornerXCoordinateTextChange)

        val topRightCornerYCoordinateObservable =
            topRightCornerYCoordinateEtv.textChanges()
                .skipInitialValue()
                .map(::trimText)
                .map(::onTopRightCornerYCoordinateTextChange)

        val roverPositionXCoordinateObservable =
            roverPositionXCoordinateEtv.textChanges()
                .skipInitialValue()
                .map(::trimText)
                .map(::onRoverPositionXCoordinateTextChange)

        val roverPositionYCoordinateObservable =
            roverPositionYCoordinateEtv.textChanges()
                .skipInitialValue()
                .map(::trimText)
                .map(::onRoverPositionYCoordinateTextChange)

        val roverDirectionObservable =
            roverDirectionEtv.textChanges()
                .skipInitialValue()
                .map(::trimText)
                .map(::onRoverDirectionTextChange)

        val roverMovementsObservable =
            roverMovementsEtv.textChanges()
                .skipInitialValue()
                .map(::trimText)
                .map(::onRoverMovementsTextChange)

        Observable.combineLatest(
            topRightCornerXCoordinateObservable,
            topRightCornerYCoordinateObservable,
            roverPositionXCoordinateObservable,
            roverPositionYCoordinateObservable,
            roverDirectionObservable,
            roverMovementsObservable,
            Function6 { topRightCornerXCoordinateValid, topRightCornerYCoordinateValid, roverPositionXCoordinateValid, roverPositionYCoordinateValid, roverDirectionValid, roverMovementsValid ->
                topRightCornerXCoordinateValid && topRightCornerYCoordinateValid && roverPositionXCoordinateValid && roverPositionYCoordinateValid && roverDirectionValid && roverMovementsValid
            }
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { enableButton ->
                sendBtn.isEnabled = enableButton
            }
            .addTo(compositeDisposable)

        sendBtn.setOnReactiveClickListener {
            mainViewModel.sendData()
        }
    }

    private fun trimText(it: CharSequence) = it.toString().trim()

    private fun onTopRightCornerXCoordinateTextChange(text: CharSequence) =
        when {
            text.isEmpty() -> {
                topRightCornerXCoordinateInputLayout.error = getString(R.string.empty_error)
                false
            }
            else -> {
                topRightCornerXCoordinateInputLayout.error = null
                true
            }
        }

    private fun onTopRightCornerYCoordinateTextChange(text: CharSequence) =
        when {
            text.isEmpty() -> {
                topRightCornerYCoordinateInputLayout.error = getString(R.string.empty_error)
                false
            }
            else -> {
                topRightCornerYCoordinateInputLayout.error = null
                true
            }
        }

    private fun onRoverPositionXCoordinateTextChange(text: CharSequence) =
        when {
            text.isEmpty() -> {
                roverPositionXCoordinateInputLayout.error = getString(R.string.empty_error)
                false
            }
            isOutsidePlateau(text, topRightCornerXCoordinateEtv) -> {
                roverPositionXCoordinateInputLayout.error = getString(R.string.outside_error)
                false
            }
            else -> {
                roverPositionXCoordinateInputLayout.error = null
                true
            }
        }

    private fun onRoverPositionYCoordinateTextChange(text: CharSequence) =
        when {
            text.isEmpty() -> {
                roverPositionYCoordinateInputLayout.error = getString(R.string.empty_error)
                false

            }
            isOutsidePlateau(text, topRightCornerYCoordinateEtv) -> {
                roverPositionYCoordinateInputLayout.error = getString(R.string.outside_error)
                false
            }
            else -> {
                roverPositionYCoordinateInputLayout.error = null
                true
            }
        }

    private fun onRoverDirectionTextChange(text: CharSequence) =
        when {
            text.isEmpty() -> {
                roverDirectionInputLayout.error = getString(R.string.empty_error)
                false
            }
            text.toString().isACardinalPoint().not() -> {
                roverDirectionInputLayout.error = getString(R.string.cardinal_error)
                false
            }
            else -> {
                roverDirectionInputLayout.error = null
                true
            }
        }

    private fun onRoverMovementsTextChange(text: CharSequence) =
        when {
            text.isEmpty() -> {
                roverMovementsInputLayout.error = getString(R.string.empty_error)
                false
            }
            text.toString().isAMovement().not() -> {
                roverMovementsInputLayout.error = getString(R.string.movement_error)
                false
            }
            else -> {
                roverMovementsInputLayout.error = null
                true
            }
        }

    private fun isOutsidePlateau(text: CharSequence, etv: EditText) =
        text.toString().toInt() > etv.text.toString().toInt()

    private fun setUpViewModel() {
        mainViewModel = viewModel(viewModelFactory.get()) {

            observe(ldLoading, ::handleLoadingState)

            observe(ldRoverData, ::showResult)
        }
    }

    private fun handleLoadingState(loading: Boolean) {
        if (loading) {
            sendBtn.gone()
            sendProgressBar.visible()
        } else {
            sendProgressBar.gone()
            sendBtn.visible()
        }
    }

    private fun showResult(result: String) {
        //TODO
    }

}
