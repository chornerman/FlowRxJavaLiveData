package com.chornerman.flowrxjavalivedata.ui.publishsubjectcustomsingleeventsharedflow

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class PublishSubjectCustomSingleEventSharedFlowViewModel : ViewModel() {

    abstract val redirectToNextScreenSharedFlow: SharedFlow<Unit>

    abstract val redirectToNextScreenPublishSubject: Observable<Unit>

    abstract suspend fun triggerRedirectToNextScreenSharedFlow()

    abstract fun triggerRedirectToNextScreenPublishSubject()
}

class PublishSubjectCustomSingleEventSharedFlowViewModelImpl :
    PublishSubjectCustomSingleEventSharedFlowViewModel() {

    private val _redirectToNextScreenSharedFlow = MutableSharedFlow<Unit>()
    override val redirectToNextScreenSharedFlow: SharedFlow<Unit>
        get() = _redirectToNextScreenSharedFlow

    private val _redirectToNextScreenPublishSubject = PublishSubject.create<Unit>()
    override val redirectToNextScreenPublishSubject: Observable<Unit>
        get() = _redirectToNextScreenPublishSubject

    override suspend fun triggerRedirectToNextScreenSharedFlow() {
        _redirectToNextScreenSharedFlow.emit(Unit)
    }

    override fun triggerRedirectToNextScreenPublishSubject() {
        _redirectToNextScreenPublishSubject.onNext(Unit)
    }
}
