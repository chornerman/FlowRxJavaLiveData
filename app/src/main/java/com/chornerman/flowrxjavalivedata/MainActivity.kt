package com.chornerman.flowrxjavalivedata

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chornerman.flowrxjavalivedata.ui.behaviorsubjectlivedatastateflow.BehaviorSubjectLiveDataStateFlowActivity
import com.chornerman.flowrxjavalivedata.ui.publishsubjectcustomsingleeventsharedflow.PublishSubjectCustomSingleEventSharedFlowActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViewEvents()
    }

    private fun bindViewEvents() {
        btBehaviorSubjectLiveDataStateFlow.setOnClickListener {
            openScreen(BehaviorSubjectLiveDataStateFlowActivity::class.java)
        }
        btPublishSubjectCustomSingleEventSharedFlow.setOnClickListener {
            openScreen(PublishSubjectCustomSingleEventSharedFlowActivity::class.java)
        }
    }

    private fun openScreen(destination: Class<*>) {
        Intent(this, destination).apply {
            startActivity(this)
        }
    }
}
