package com.nikhil.startertemplate.screens.host

import com.nikhil.startertemplate.base.BaseEvent

sealed class HostEvent : BaseEvent {
    object GoToLanding : HostEvent()
    data class GoToSetSimulator(val name: String?, val initialSet: Boolean) : HostEvent()
}
