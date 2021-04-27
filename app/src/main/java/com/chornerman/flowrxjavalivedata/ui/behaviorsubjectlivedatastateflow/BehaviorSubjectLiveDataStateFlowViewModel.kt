package com.chornerman.flowrxjavalivedata.ui.behaviorsubjectlivedatastateflow

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BehaviorSubjectLiveDataStateFlowViewModel : ViewModel() {

    abstract val shouldShowLoadingStateFlow: StateFlow<Boolean>

    abstract val shouldShowLoadingBehaviorSubject: Observable<Boolean>

    abstract fun loadOrStopStateFlow()

    abstract fun loadOrStopBehaviorSubject()
}

class BehaviorSubjectLiveDataStateFlowViewModelImpl : BehaviorSubjectLiveDataStateFlowViewModel() {

    private val _showLoadingStateFlow = MutableStateFlow(true)
    override val shouldShowLoadingStateFlow: StateFlow<Boolean>
        get() = _showLoadingStateFlow

    private val _showLoadingBehaviorSubject = BehaviorSubject.createDefault(true)
    override val shouldShowLoadingBehaviorSubject: Observable<Boolean>
        get() = _showLoadingBehaviorSubject

    override fun loadOrStopStateFlow() {
        _showLoadingStateFlow.value = !_showLoadingStateFlow.value
    }

    override fun loadOrStopBehaviorSubject() {
        _showLoadingBehaviorSubject.onNext(!requireNotNull(_showLoadingBehaviorSubject.value))
    }
}
