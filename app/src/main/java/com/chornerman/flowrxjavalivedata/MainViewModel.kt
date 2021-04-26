package com.chornerman.flowrxjavalivedata

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

abstract class MainViewModel : ViewModel() {

    abstract val shouldShowLoading: StateFlow<Boolean>

    abstract val redirectToNextScreen: SharedFlow<Unit>

    abstract fun loadOrStop()

    abstract suspend fun triggerRedirectToNextScreen()
}

class MainViewModelImpl : MainViewModel() {

    private val _showLoading = MutableStateFlow(true)
    private val _redirectToNextScreen = MutableSharedFlow<Unit>()

    override val redirectToNextScreen: SharedFlow<Unit>
        get() = _redirectToNextScreen

    override fun loadOrStop() {
        _showLoading.value = !_showLoading.value
    }

    override val shouldShowLoading: StateFlow<Boolean>
        get() = _showLoading

    override suspend fun triggerRedirectToNextScreen() {
        _redirectToNextScreen.emit(Unit)
    }
}
