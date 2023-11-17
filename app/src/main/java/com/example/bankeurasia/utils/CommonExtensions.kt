package com.example.bankeurasia.utils

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.bankeurasia.R
import com.squareup.picasso.Picasso
import dev.chrisbanes.insetter.Insetter
import dev.chrisbanes.insetter.windowInsetTypesOf
import java.text.SimpleDateFormat
import java.util.Locale

const val url = "https://openweathermap.org/img/wn/"
const val urlSize = "@2x.png"

fun Fragment.setSupportActionBar(
    toolbar: Toolbar?,
    @DrawableRes navigationIcon: Int? = R.drawable.ic_back
) {
    val activity = activity
    if (activity is AppCompatActivity) {
        if (navigationIcon != null) {
            toolbar?.setNavigationIcon(navigationIcon)
        }
        activity.setSupportActionBar(toolbar)
    }
    toolbar?.let {
        Insetter.builder()
            .padding(windowInsetTypesOf(statusBars = true))
            .applyToView(it)
    }
}

val Fragment.canPerformTransaction: Boolean?
    get() = activity?.lifecycle?.currentState?.isAtLeast(Lifecycle.State.RESUMED)

fun String?.toNullIfBlank(): String? {
    if (this.isNullOrBlank()) {
        return null
    }
    return this
}

fun ImageView.load(iconUrl: String?) {
    Picasso.get().load(iconUrl.toNullIfBlank()).into(this)
}

fun DialogFragment.show(fragment: Fragment?) {
    if (fragment?.canPerformTransaction == true) {
        show(fragment.childFragmentManager, javaClass.simpleName)
    }
}

fun Int.toColor(context: Context) = ContextCompat.getColor(context, this)
fun Int.toDimen(context: Context) = context.resources.getDimensionPixelSize(this)


fun getDayOfWeek(dateString: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = dateFormat.parse(dateString)
    val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    return dayOfWeekFormat.format(date)
}

