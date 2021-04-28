package co.nimblehq.flowrxjavalivedata.ui.behaviorsubjectlivedatastateflow

import androidx.lifecycle.*
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BehaviorSubjectLiveDataStateFlowViewModel : ViewModel() {

    abstract val shouldShowLoadingStateFlow: StateFlow<Boolean>

    abstract val shouldShowLoadingBehaviorSubject: Observable<Boolean>

    abstract val shouldShowLoadingLiveData: LiveData<Boolean>

    abstract fun loadOrStopStateFlow()

    abstract fun loadOrStopBehaviorSubject()

    abstract fun loadOrStopLiveData()
}

class BehaviorSubjectLiveDataStateFlowViewModelImpl : BehaviorSubjectLiveDataStateFlowViewModel() {

    private val _showLoadingStateFlow = MutableStateFlow(true)
    override val shouldShowLoadingStateFlow: StateFlow<Boolean>
        get() = _showLoadingStateFlow

    private val _showLoadingBehaviorSubject = BehaviorSubject.createDefault(true)
    override val shouldShowLoadingBehaviorSubject: Observable<Boolean>
        get() = _showLoadingBehaviorSubject

    private val _showLoadingLiveData = MutableLiveData(true)
    override val shouldShowLoadingLiveData: LiveData<Boolean>
        get() = _showLoadingLiveData

    override fun loadOrStopStateFlow() {
        _showLoadingStateFlow.value = !_showLoadingStateFlow.value
    }

    override fun loadOrStopBehaviorSubject() {
        _showLoadingBehaviorSubject.onNext(!requireNotNull(_showLoadingBehaviorSubject.value))
    }

    override fun loadOrStopLiveData() {
        _showLoadingLiveData.value = !requireNotNull(_showLoadingLiveData.value)
    }
}
