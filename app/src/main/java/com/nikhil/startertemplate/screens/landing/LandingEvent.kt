package com.nikhil.startertemplate.screens.landing

import com.nikhil.startertemplate.base.BaseEvent

sealed class LandingEvent: BaseEvent {
    object GoToHome: LandingEvent()
    data class GoToSetSimulator(val name: String?): LandingEvent()
}