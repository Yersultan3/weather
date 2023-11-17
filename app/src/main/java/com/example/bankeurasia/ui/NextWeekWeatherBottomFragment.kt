package com.example.bankeurasia.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import com.example.bankeurasia.databinding.FragmentNextWeekBottomBinding
import com.example.bankeurasia.model.WeatherData
import com.example.bankeurasia.ui.adapter.NextWeekItemAdapter
import com.example.bankeurasia.utils.getDayOfWeek
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Locale

class NextWeekWeatherBottomFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNextWeekBottomBinding

    private val argument: WeatherData? by lazy {
        requireArguments().getParcelable(ARGUMENTS)
    }

    private val adapter = NextWeekItemAdapter()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return createDialog(savedInstanceState)
    }

    private fun createDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { bottom ->
            val d = bottom as BottomSheetDialog
            val bottomSheet =
                d.findViewById(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            BottomSheetBehavior.from(bottomSheet!!).apply {
                skipCollapsed = true
                state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNextWeekBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        val uniqueData = argument?.list?.distinctBy { getDayOfWeek(it.dtTxt) }
        adapter.submitList(uniqueData)
    }

    companion object {

        private const val ARGUMENTS = "argument"

        fun newInstance(agreement: WeatherData?) = NextWeekWeatherBottomFragment().apply {
            arguments = bundleOf(Pair(ARGUMENTS, agreement))
        }
    }
}