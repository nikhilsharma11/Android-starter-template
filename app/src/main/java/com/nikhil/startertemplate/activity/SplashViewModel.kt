package com.nikhil.startertemplate.activity

import androidx.lifecycle.viewModelScope
import com.nikhil.startertemplate.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel() : BaseViewModel<SplashEvent>() {

    init {
        viewModelScope.launch {
            delay(SPLASH_DELAY)
            postEvent(SplashEvent.GoToHostScreen)
        }
    }

    companion object {
        private const val SPLASH_DELAY = 2000L
    }

}