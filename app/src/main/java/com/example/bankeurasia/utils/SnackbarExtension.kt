package com.example.bankeurasia.utils

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.setMargins
import androidx.core.view.updateLayoutParams
import com.example.bankeurasia.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.R as Rmaterial

fun View.showSnackBar(
    message: String?,
    duration: Int = Snackbar.LENGTH_SHORT,
    viewGravity: Int = Gravity.TOP,
    @ColorInt colorInt: Int = context.colorPrimary,
    @DrawableRes backgroundRes: Int = R.drawable.shape_snackbar,
    @DimenRes offSet: Int = R.dimen.snackbar_offset,
) {
    if (message.isNullOrEmpty()) return
    Snackbar.make(this, message, duration).apply {
        setBackgroundTint(colorInt)
        view.updateLayoutParams {
            width = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        view.setBackgroundResource(backgroundRes)
        view.findViewById<TextView>(Rmaterial.id.snackbar_text).apply {
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            setTextColor(R.color.snackbar_text_color.toColor(context))
        }
        view.layoutParams = when (view.layoutParams) {
            is FrameLayout.LayoutParams -> {
                (view.layoutParams as FrameLayout.LayoutParams).apply {
                    gravity = viewGravity or Gravity.CENTER
                    setMargins(offSet.toDimen(context))
                }
            }

            is CoordinatorLayout.LayoutParams -> {
                (view.layoutParams as CoordinatorLayout.LayoutParams).apply {
                    gravity = viewGravity or Gravity.CENTER
                    setMargins(offSet.toDimen(context))
                }
            }

            else -> view.layoutParams
        }
    }.show()
}

