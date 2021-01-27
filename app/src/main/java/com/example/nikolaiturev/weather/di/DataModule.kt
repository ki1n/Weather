package com.example.nikolaiturev.weather.di

import com.example.nikolaiturev.weather.BuildConfig
import com.example.nikolaiturev.weather.data.api.WeatherApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    single<OkHttpClient> {
        val logsInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val headerInterceptor = Interceptor { chain ->
            val builder = chain
                .request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                /*.addHeader("Platform", "android")*/
//            androidContext().userPreference.authToken.let {
//                if (it.isNotEmpty()) {
//                    builder.addHeader("Authorization", it)
//                }
//            }
            chain.proceed(builder.build())
        }

        OkHttpClient.Builder()
            //.addInterceptor(headerInterceptor)
            .addInterceptor(logsInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single<Gson> {
        GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .enableComplexMapKeySerialization()
            .setVersion(1.0)
            .create()
    }

    single<WeatherApi> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(WeatherApi::class.java)
    }

}