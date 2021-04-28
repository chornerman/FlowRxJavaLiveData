package co.nimblehq.flowrxjavalivedata.ui.publishsubjectcustomsingleeventsharedflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class PublishSubjectCustomSingleEventSharedFlowViewModel : ViewModel() {

    abstract val redirectToNextScreenSharedFlow: SharedFlow<Unit>

    abstract val redirectToNextScreenPublishSubject: Observable<Unit>

    abstract val redirectToNextScreenCustomSingleEvent: LiveData<Unit>

    abstract suspend fun triggerRedirectToNextScreenSharedFlow()

    abstract fun triggerRedirectToNextScreenPublishSubject()

    abstract fun triggerRedirectToNextScreenCustomSingleEvent()
}

class PublishSubjectCustomSingleEventSharedFlowViewModelImpl :
    PublishSubjectCustomSingleEventSharedFlowViewModel() {

    private val _redirectToNextScreenSharedFlow = MutableSharedFlow<Unit>()
    override val redirectToNextScreenSharedFlow: SharedFlow<Unit>
        get() = _redirectToNextScreenSharedFlow

    private val _redirectToNextScreenPublishSubject = PublishSubject.create<Unit>()
    override val redirectToNextScreenPublishSubject: Observable<Unit>
        get() = _redirectToNextScreenPublishSubject

    // https://github.com/hadilq/LiveEvent
    private val _redirectToNextScreenCustomSingleEvent = LiveEvent<Unit>()
    override val redirectToNextScreenCustomSingleEvent: LiveData<Unit>
        get() = _redirectToNextScreenCustomSingleEvent

    override suspend fun triggerRedirectToNextScreenSharedFlow() {
        _redirectToNextScreenSharedFlow.emit(Unit)
    }

    override fun triggerRedirectToNextScreenPublishSubject() {
        _redirectToNextScreenPublishSubject.onNext(Unit)
    }

    override fun triggerRedirectToNextScreenCustomSingleEvent() {
        _redirectToNextScreenCustomSingleEvent.value = Unit
    }
}
