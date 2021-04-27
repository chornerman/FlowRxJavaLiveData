package com.chornerman.flowrxjavalivedata.ui.behaviorsubjectlivedatastateflow

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.chornerman.flowrxjavalivedata.R
import com.google.android.material.button.MaterialButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_behavior_subject_live_data_state_flow.*
import kotlinx.coroutines.flow.collect

class BehaviorSubjectLiveDataStateFlowActivity : AppCompatActivity() {

    private val viewModel by viewModels<BehaviorSubjectLiveDataStateFlowViewModelImpl>()

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavior_subject_live_data_state_flow)
        bindViewEvents()
        bindViewModel()
    }

    private fun bindViewEvents() {
        btStateFlowLoadOrStop.setOnClickListener {
            viewModel.loadOrStopStateFlow()
        }
        btBehaviorSubjectLoadOrStop.setOnClickListener {
            viewModel.loadOrStopBehaviorSubject()
        }
        btLiveDataLoadOrStop.setOnClickListener {
            viewModel.loadOrStopLiveData()
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.shouldShowLoadingStateFlow.collect { shouldShowLoading ->
                showHideLoading(
                    shouldShowLoading = shouldShowLoading,
                    progressBar = pbStateFlow,
                    loadOrStopButton = btStateFlowLoadOrStop
                )
            }
        }

        val disposable = viewModel.shouldShowLoadingBehaviorSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { shouldShowLoading ->
                showHideLoading(
                    shouldShowLoading = shouldShowLoading,
                    progressBar = pbBehaviorSubject,
                    loadOrStopButton = btBehaviorSubjectLoadOrStop
                )
            }
        disposables.add(disposable)

        viewModel.shouldShowLoadingLiveData.observe(this, { shouldShowLoading ->
            showHideLoading(
                shouldShowLoading = shouldShowLoading,
                progressBar = pbLiveData,
                loadOrStopButton = btLiveDataLoadOrStop
            )
        })
    }

    private fun showHideLoading(
        shouldShowLoading: Boolean,
        progressBar: ProgressBar,
        loadOrStopButton: MaterialButton
    ) {
        if (shouldShowLoading) {
            progressBar.visibility = View.VISIBLE
            loadOrStopButton.text = getString(R.string.stop_label)
        } else {
            progressBar.visibility = View.GONE
            loadOrStopButton.text = getString(R.string.load_label)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}
