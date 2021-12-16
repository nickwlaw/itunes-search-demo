package com.nickwlaw.itunessearchdemo.foundation

import android.content.Intent
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import timber.log.Timber

sealed interface UIState {
    interface ErrorLoadingUIState: UIState {
        val errorUiState: ErrorUiState
        val isLoading: Boolean
    }

    interface BasicUiState: UIState
}

data class ErrorUiState(val hasError: Boolean = false, val errorMessage: String = "Something went wrong")

abstract class BaseViewModel<UiStateType : UIState>(initialState: UiStateType) : ViewModel() {

    val uiState: UiStateType get() = _uiStateLiveData.value ?: throw IllegalStateException("UiState is null")

    private val _uiStateLiveData: MutableLiveData<UiStateType?> = MutableLiveData(null)
    val uiStateLiveData: LiveData<UiStateType?> get() = _uiStateLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e("$exception")
    }

    init {
        _uiStateLiveData.value = initialState
    }

    open fun onArgumentsReceived(arguments: Map<String, String>) {}

    open fun onResultReceived(request: Int, result: Int, data: Intent?) {}

    fun updateState(uiState: UiStateType) {
        _uiStateLiveData.value = uiState
    }

    abstract suspend fun onStart(arguments: Map<String, String>? = null)

    fun interact(fetch: suspend () -> Unit) {
        viewModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO) {
                runCatching {
                    withContext(Dispatchers.Main) {
                        fetch()
                    }
                }
                    .onFailure {
                        withContext(Dispatchers.Main) {
                            onError(it)
                        }
                    }
            }
        }
    }

    suspend fun <T> interact(fetch: suspend () -> T): Deferred<T> {
        return withContext(Dispatchers.IO) {
            async(exceptionHandler) { fetch() }
        }
    }

    @CallSuper
    open fun onError(error: Throwable) {
        Timber.e("$error")
    }
}


