package com.nikhil.startertemplate.activity

import com.nikhil.startertemplate.base.BaseEvent


sealed class SplashEvent : BaseEvent {
    object GoToHostScreen: SplashEvent()
}