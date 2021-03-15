package com.example.nikolaiturev.weather.presentation.choice_city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikolaiturev.weather.R
import com.example.nikolaiturev.weather.domain.entity.City
import com.example.nikolaiturev.weather.presentation.choice_city.adapter.ChoiceCityAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_choice_city.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChoiceCityDialog(var onResult: ((String) -> Unit)? = null) : BottomSheetDialogFragment() {

    private val choiceWeatherAdapter: ChoiceCityAdapter by inject()

    private val viewModel by viewModel<ChoiceCityViewModel>()

    override fun getTheme(): Int = R.style.Theme_FullScreenDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setStyle(STYLE_NORMAL, R.style.Theme_FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choice_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        edSearchCity.doOnTextChanged { text, _, _, _ ->
            viewModel.listCityFilter.value = City(text.toString().toLowerCase())
        }

        viewModel.listCity.observe(viewLifecycleOwner, { list ->
            choiceWeatherAdapter.submitList(list ?: emptyList())
        })
        viewModel.nameFilterCity.observe(viewLifecycleOwner, { list ->
            list.let {
                choiceWeatherAdapter.submitList(list ?: emptyList())
            }
        })
    }

    private fun initAdapter() {
        list_city.adapter = choiceWeatherAdapter
        list_city.layoutManager = LinearLayoutManager(requireContext())

        choiceWeatherAdapter.onClick = {
            onResult?.invoke(it)
            dismiss()
        }
    }
}
