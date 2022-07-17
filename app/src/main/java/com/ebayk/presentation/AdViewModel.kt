package com.ebayk.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ebayk.baseviewmodel.BaseViewModel
import com.ebayk.baseviewmodel.CoroutineDispatcherProvider
import com.ebayk.core.model.Ad
import com.ebayk.core.usecase.GetAdDetailUseCase

import com.ebayk.util.AD_ID
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AdViewModel(
    coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val useCase: GetAdDetailUseCase,
) : BaseViewModel(
    coroutineContexts = coroutineDispatcherProvider
) {
    private val _adDetail = MutableLiveData<Ad>()
    val adDetail: LiveData<Ad>
        get() = _adDetail

    private fun onError(message: String) {
        _errorMessage.postValue(message)
        _loading.postValue(false)
    }

    fun getAdDetail() {
        viewModelScope.launch {
            withContext(ioDispatcher()) {
                lateinit var response: Ad
                _loading.postValue(true)
                runCatching {
                    response = useCase.execute(AD_ID)
                    println("true")
                }.onSuccess {
                    println(response.toString())
                    _adDetail.postValue(response)
                    _loading.postValue(false)
                }.onFailure {
                    it.message?.let { notNullThrowable -> onError(notNullThrowable) }
                }
            }
        }
    }


}