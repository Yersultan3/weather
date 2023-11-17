package com.example.bankeurasia.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.bankeurasia.R
import com.example.bankeurasia.base.BaseFragment
import com.example.bankeurasia.databinding.FragmentSelectCityBinding
import com.example.bankeurasia.utils.EmptyTextValidator
import com.example.bankeurasia.utils.Validator
import com.example.bankeurasia.utils.isValid
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.Insetter
import dev.chrisbanes.insetter.windowInsetTypesOf


@AndroidEntryPoint
class SelectCityFragment : BaseFragment() {

    private val binding: FragmentSelectCityBinding by viewBinding()

    override val layoutId: Int = R.layout.fragment_select_city

    private var validators = mutableListOf<Validator>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validators.add(
            EmptyTextValidator(
                binding.root, binding
                    .enterCityEditText
            )
        )
        binding.selectCityButton.setOnClickListener {
            if (validators.isValid()) {
                val action =
                    SelectCityFragmentDirections.actionFragmentSelectCityToFragmentMain(binding.enterCityEditText.text.toString())
                findNavController().navigate(action)
            }
        }
    }

    override fun applyInsets() {
        Insetter.builder()
            .padding(windowInsetTypesOf(statusBars = true, navigationBars = true))
            .applyToView(binding.root)
    }
}