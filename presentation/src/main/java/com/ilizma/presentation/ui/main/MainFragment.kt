package com.ilizma.presentation.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.observe
import com.ilizma.presentation.extensions.viewModel
import com.ilizma.presentation.ui.base.BaseFragment
import dagger.Lazy
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
        setUpViewModel()
    }

    private fun setUpToolBar() {
        (activity as MainActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
    }

    private fun setUpViewModel() {
        mainViewModel = viewModel(viewModelFactory.get()) {

            observe(ldLoading) {}

            observe(ldRoverData) {}
        }
    }

}
