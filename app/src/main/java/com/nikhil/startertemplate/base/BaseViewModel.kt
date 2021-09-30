package com.nikhil.startertemplate.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikhil.startertemplate.common.SingleLiveEvent
import com.nikhil.startertemplate.common.models.ProjectError
import com.nikhil.startertemplate.common.models.ProjectErrorJsonAdapter
import com.nikhil.startertemplate.data.DataRepositoryContract
import com.nikhil.startertemplate.data.idlingresource.ProjectIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import java.security.InvalidParameterException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<T : BaseEvent> : ViewModel(), KoinComponent {

    private val dataRepository: DataRepositoryContract by inject()
    private val projectErrorJsonAdapter: ProjectErrorJsonAdapter by inject()
    private val idlingResource: ProjectIdlingResource by inject()

    private val _events = SingleLiveEvent<T>()
    val events: LiveData<T>
        get() = _events

    private val _apiError = SingleLiveEvent<ProjectError>()
    val apiError: LiveData<ProjectError>
        get() = _apiError

    private val _loaderVisibility = MutableLiveData<Boolean>()
    val loaderVisibility: LiveData<Boolean>
        get() = _loaderVisibility

    protected fun postEvent(event: T?, sync: Boolean = false) {
        if (sync) {
            _events.value = event
        } else {
            _events.postValue(event)
        }
    }

//	/**
//	 * Broadcast multiple [T] with a single post. This is so that you don't need to manually handle
//	 * adding a delay in between individual [T] posts.
//	 *
//	 * The observer of [eventSets] will receive each of the events at a single time.
//	 */
//	protected fun postEventSet(vararg events: T) {
//		_eventSets.postValue(events.toList())
//	}

    protected fun postApiError(apiError: Throwable) {
        val projectError = ProjectError.fromError(projectErrorJsonAdapter, apiError)
        if (projectError != null) {
            _apiError.postValue(projectError)
        } else {
            Timber.e(InvalidParameterException("Failed to parse api error as ProjectError."))
            apiError.printStackTrace()
        }
    }

    protected fun showLoader() {
        _loaderVisibility.postValue(true)
    }

    protected fun hideLoader() {
        _loaderVisibility.postValue(false)
    }

    protected fun CoroutineScope.launchIdling(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        idlingResource.increment()
        val job = this.launch(context, start, block)
        job.invokeOnCompletion { idlingResource.decrement() }
        return job
    }

}