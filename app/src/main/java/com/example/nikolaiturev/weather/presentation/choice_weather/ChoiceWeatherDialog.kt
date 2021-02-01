package com.example.nikolaiturev.weather.presentation.choice_weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikolaiturev.weather.R
import com.example.nikolaiturev.weather.presentation.choice_weather.adapter.ChoiceWeatherAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_choice_weather.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChoiceWeatherDialog : BottomSheetDialogFragment() {

    private val choiceWeatherAdapter: ChoiceWeatherAdapter by inject()

    private val viewModel by viewModel<ChoiceWeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choice_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        viewModel.getCity()

        viewModel.listLocation.observe(viewLifecycleOwner, { list ->
            choiceWeatherAdapter.updateCityWeather(list ?: emptyList())
        })

        bottomSheet.setOnClickListener {
            dialog?.dismiss()
        }

        btSearch.setOnClickListener {

        }
    }

    private fun initAdapter() {
        list_city.adapter = choiceWeatherAdapter
        list_city.layoutManager = LinearLayoutManager(requireContext())
    }

}