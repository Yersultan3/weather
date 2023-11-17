package com.example.bankeurasia.utils

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.bankeurasia.R

class EmptyTextValidator(
    private val rootView: View?,
    private val textView: TextView,
    private val message: String? = rootView?.context?.getString(R.string.fill_all_fields)
) : Validator {

    override fun isValid(): Boolean {
        if (textView.isVisible && textView.text.isNullOrBlank()) {
            rootView?.showSnackBar(message)
            return false
        }
        return true
    }
}