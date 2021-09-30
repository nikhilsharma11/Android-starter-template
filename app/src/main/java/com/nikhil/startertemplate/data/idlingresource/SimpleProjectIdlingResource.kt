package com.nikhil.startertemplate.data.idlingresource

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource
import timber.log.Timber

class SimpleProjectIdlingResource: ProjectIdlingResource {

    private val idlingResource = CountingIdlingResource("Remote idling resource", true)

    override fun getResource(): IdlingResource? = idlingResource

    override fun increment() {
        idlingResource.increment()
        Timber.d("Increment")
        idlingResource.dumpStateToLogs()
    }

    override fun decrement() {
        idlingResource.decrement()
        Timber.d("Decrement")
        idlingResource.dumpStateToLogs()
    }

}