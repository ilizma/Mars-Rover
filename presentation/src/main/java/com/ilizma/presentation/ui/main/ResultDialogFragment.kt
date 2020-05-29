package com.ilizma.presentation.ui.main

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.getSerializableParam
import com.ilizma.presentation.extensions.setOnReactiveClickListener
import com.ilizma.presentation.extensions.setSerializableParam
import kotlinx.android.synthetic.main.fragment_result_dialog.*

class ResultDialogFragment : DialogFragment() {

    companion object {
        fun getFragment(result: String) =
            ResultDialogFragment().apply {
                setSerializableParam(result)
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return context?.let { safeContext ->
            Dialog(safeContext, R.style.AppTheme_DialogFragment)
        } ?: super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_result_dialog, container, false)

    override fun onResume() {
        super.onResume()
        dialog?.window?.let { window ->
            val params = window.attributes
            params.width = ConstraintLayout.LayoutParams.MATCH_PARENT
            params.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
            window.attributes = params
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result: String = getSerializableParam()
        resultTxv.text = getString(R.string.result, result)
        okBtn.setOnReactiveClickListener {
            dismiss()
        }
    }

}