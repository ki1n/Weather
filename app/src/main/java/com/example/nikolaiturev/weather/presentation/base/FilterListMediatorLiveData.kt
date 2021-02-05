package com.example.nikolaiturev.weather.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FilterListMediatorLiveData<F, T>(
        private val dataLiveData: LiveData<List<T>>,
        private val filterLiveData: LiveData<F>,
        private val filter: (T, F?) -> Boolean
) : MediatorLiveData<List<T>>() {
    private var disposable: Disposable? = null

    init {
        this.addSource(dataLiveData) {
            applyFilters()
        }
        this.addSource(filterLiveData) {
            applyFilters()
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (disposable?.isDisposed == false) {
            disposable?.dispose()
        }
    }

    private fun applyFilters() {
        disposable = Single.create<List<T>> { emitter ->
            dataLiveData.value?.let {
                emitter.onSuccess(it.filter {
                    filter.invoke(it, filterLiveData.value)
                }
                )
            } ?: kotlin.run {
                emitter.onSuccess(mutableListOf())
            }
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                this.value = it
            }, {
                //ignore
            })
    }
}