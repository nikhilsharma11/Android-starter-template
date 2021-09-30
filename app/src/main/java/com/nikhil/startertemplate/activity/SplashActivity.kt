package com.nikhil.startertemplate.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.nikhil.startertemplate.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<SplashEvent, SplashViewModel>() {
    override val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.events.observe(this, Observer {
            when (it) {
                is SplashEvent.GoToHostScreen -> {
                    startActivity(HostActivity.getIntent(this))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            }
        })
    }
}