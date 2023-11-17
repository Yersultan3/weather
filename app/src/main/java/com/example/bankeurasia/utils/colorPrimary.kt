package com.example.bankeurasia.utils

import android.content.Context
import android.util.TypedValue
import androidx.appcompat.R as Rappcompat

inline val Context.colorPrimary: Int
    get() {
        val value = TypedValue()
        theme.resolveAttribute(Rappcompat.attr.colorPrimary, value, true)
        return value.data
    }