package com.chornerman.flowrxjavalivedata

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViewEvents()
        bindViewModel()
    }

    private fun bindViewEvents() {
        btLoadOrStop.setOnClickListener {
            viewModel.loadOrStop()
        }

        btNextScreen.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                viewModel.triggerRedirectToNextScreen()
            }
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.shouldShowLoading.collect { shouldShowLoading ->
                if (shouldShowLoading) {
                    progressBar.visibility = View.VISIBLE
                    btLoadOrStop.text = getString(R.string.stop_label)

                } else {
                    progressBar.visibility = View.GONE
                    btLoadOrStop.text = getString(R.string.load_label)
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.redirectToNextScreen.collect {
                redirectToNextScreen()
            }
        }
    }

    private fun redirectToNextScreen() {
        Intent(this, SecondActivity::class.java).apply {
            startActivity(this)
        }
    }
}
