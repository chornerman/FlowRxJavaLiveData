package co.nimblehq.flowrxjavalivedata.ui.publishsubjectcustomsingleeventsharedflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import co.nimblehq.flowrxjavalivedata.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_publish_subject_custom_single_event_shared_flow.*
import kotlinx.coroutines.flow.collect

class PublishSubjectCustomSingleEventSharedFlowActivity : AppCompatActivity() {

    private val viewModel by viewModels<PublishSubjectCustomSingleEventSharedFlowViewModelImpl>()

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish_subject_custom_single_event_shared_flow)
        bindViewEvents()
        bindViewModel()
    }

    private fun bindViewEvents() {
        btSharedFlowNextScreen.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                viewModel.triggerRedirectToNextScreenSharedFlow()
            }
        }
        btPublishSubjectNextScreen.setOnClickListener {
            viewModel.triggerRedirectToNextScreenPublishSubject()
        }
        btCustomSingleEventNextScreen.setOnClickListener {
            viewModel.triggerRedirectToNextScreenCustomSingleEvent()
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.redirectToNextScreenSharedFlow.collect {
                redirectToNextScreen()
            }
        }

        val disposable = viewModel.redirectToNextScreenPublishSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { redirectToNextScreen() }
        disposables.add(disposable)

        viewModel.redirectToNextScreenCustomSingleEvent.observe(this, {
            redirectToNextScreen()
        })
    }

    private fun redirectToNextScreen() {
        Intent(this, SecondActivity::class.java).apply {
            startActivity(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}
