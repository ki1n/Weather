package com.example.nikolaiturev.weather.presentation.weather

import com.bumptech.glide.Glide
import com.example.nikolaiturev.weather.BuildConfig
import com.example.nikolaiturev.weather.R
import com.example.nikolaiturev.weather.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : BaseActivity() {

    override var layoutId: Int = R.layout.activity_weather

    private val viewModel by viewModel<WeatherViewModel>()

    //TODO: pls update this func
    override fun iniView() {
        viewModel.getWeather("London")

        viewModel.weatherLiveData.observe(this, { weatherResponce ->
            with(weatherResponce) {
                tvTemperature.text = tempC.toInt().toString()
                tvCity.text = name
                tvСlimate.text = description
                tvWindTxt.text = getString(R.string.ms, speed.toString())
                tvPressureTxt.text = getString(R.string.mm_rt_ct, pressure.toString())
                tvHumidityTxt.text = getString(R.string.percent, humidity.toString())

                Glide.with(imvWeather.context)
                    .load(BuildConfig.ICON_URL + icon + "@2x.png")
                    .error(R.drawable.ic_error_connect_icon)
                    .into(imvWeather)
            }
        })

        /*rbChoice.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.radioC -> {
                        tvTemperature.text =
                            viewModel.translateCelsius(weatherResponce.main.temp.toInt().toString())
                                .toString()
                    }
                    R.id.radioF -> {
                        tvTemperature.text =
                            viewModel.translateFahrenheit(
                                weatherResponce.main.temp.toInt().toString()
                            ).toString()
                    }
                }
            }*/

        /* tvSweepCity.setOnClickListener {
             val btnsheet = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)
             val dialog = BottomSheetDialog(this)
             dialog.setContentView(btnsheet)

             fun View.setVisible(visible: Boolean) {
                 visibility = if (visible) {
                     View.VISIBLE
                 } else {
                     View.GONE
                 }
             }

             val imgMoscow: ImageView? = dialog.findViewById(R.id.imgMoscow)
             val imgPetersburg: ImageView? = dialog.findViewById(R.id.imgPetersburg)
             val imgOmsk: ImageView? = dialog.findViewById(R.id.imgOmsk)
             val imgSamara: ImageView? = dialog.findViewById(R.id.imgSamara)
             val imgSochi: ImageView? = dialog.findViewById(R.id.imgSochi)

             val moscow: TextView? = dialog.findViewById(R.id.tvMoscow)
             val petersburg: TextView? = dialog.findViewById(R.id.tvPetersburg)
             val omsk: TextView? = dialog.findViewById(R.id.tvOmsk)
             val samara: TextView? = dialog.findViewById(R.id.tvSamara)
             val sochi: TextView? = dialog.findViewById(R.id.tvSochi)

             val search: TextView? = dialog.findViewById(R.id.btSearch)
             var textCity = ""

             search?.setOnClickListener {
                 viewModel.getWeather(textCity)
                 dialog.dismiss()
             }

             moscow?.setOnClickListener {
                 imgMoscow?.visibility = View.VISIBLE
                 imgPetersburg?.visibility = View.GONE
                 imgOmsk?.visibility = View.GONE
                 imgSamara?.visibility = View.GONE
                 imgSochi?.visibility = View.GONE
                 if (imgMoscow != null) {
                     if (imgMoscow.isVisible) {
                         textCity = moscow.text.toString()
                     }
                 }

             }
             petersburg?.setOnClickListener {
                 imgMoscow?.visibility = View.GONE
                 imgPetersburg?.visibility = View.VISIBLE
                 imgOmsk?.visibility = View.GONE
                 imgSamara?.visibility = View.GONE
                 imgSochi?.visibility = View.GONE
                 if (imgPetersburg != null) {
                     if (imgPetersburg.isVisible) {
                         textCity = petersburg.text.toString()
                     }
                 }

             }
             omsk?.setOnClickListener {
                 imgMoscow?.visibility = View.GONE
                 imgPetersburg?.visibility = View.GONE
                 imgOmsk?.visibility = View.VISIBLE
                 imgSamara?.visibility = View.GONE
                 imgSochi?.visibility = View.GONE
                 if (imgOmsk != null) {
                     if (imgOmsk.isVisible) {
                         textCity = omsk.text.toString()
                     }
                 }
             }

             samara?.setOnClickListener {
                 imgMoscow?.visibility = View.GONE
                 imgPetersburg?.visibility = View.GONE
                 imgOmsk?.visibility = View.GONE
                 imgSamara?.visibility = View.VISIBLE
                 imgSochi?.visibility = View.GONE
                 if (imgSamara != null) {
                     if (imgSamara.isVisible) {
                         textCity = samara.text.toString()
                     }
                 }
             }
             sochi?.setOnClickListener {
                 imgMoscow?.visibility = View.GONE
                 imgPetersburg?.visibility = View.GONE
                 imgOmsk?.visibility = View.GONE
                 imgSamara?.visibility = View.GONE
                 imgSochi?.visibility = View.VISIBLE
                 if (imgSochi != null) {
                     if (imgSochi.isVisible) {
                         textCity = sochi.text.toString()
                     }
                 }
             }

             btnsheet.setOnClickListener {
                 dialog.dismiss()
             }
             dialog.show()
         }*/

    }
}