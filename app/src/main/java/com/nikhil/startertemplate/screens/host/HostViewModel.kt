package com.nikhil.startertemplate.screens.host

import com.nikhil.startertemplate.base.BaseViewModel
import com.nikhil.startertemplate.data.DataRepositoryContract

class HostViewModel(
    private val dataRepository: DataRepositoryContract
) : BaseViewModel<HostEvent>() {

    fun goToLanding() {
        postEvent(HostEvent.GoToLanding)
    }

    fun goToSetSimulator(name: String?, initialSet: Boolean) {
        postEvent(HostEvent.GoToSetSimulator(name, initialSet))
    }

}