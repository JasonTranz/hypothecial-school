package com.jason.littlelives.ui.feature.splash.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): ViewModel() {

    private val _loadingComplete = MutableStateFlow(false)
    val loadingComplete = _loadingComplete.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            _loadingComplete.update { true }
        }
    }
}