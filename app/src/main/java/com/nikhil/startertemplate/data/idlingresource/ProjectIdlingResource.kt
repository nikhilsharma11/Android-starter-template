package com.nikhil.startertemplate.data.idlingresource

import androidx.test.espresso.IdlingResource

interface ProjectIdlingResource {
    fun getResource(): IdlingResource?
    fun increment()
    fun decrement()
}