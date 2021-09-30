package com.nikhil.startertemplate.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.nikhil.startertemplate.common.models.ProjectError
import com.nikhil.startertemplate.screens.host.HostViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseNavHostFragment<E : BaseEvent, VM: BaseViewModel<E>> : NavHostFragment(), BaseFragmentContract {

    abstract val viewModel: VM

    /**
     * Every fragment is a child of [HostActivity] which has [HostViewModel].
     */
    private val hostViewModel: HostViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loaderVisibility.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> showLoader()
                false -> hideLoader()
            }
        })
        viewModel.apiError.observe(viewLifecycleOwner, Observer {
            handleError(it)
        })
    }

    override fun showLoader() {
        (activity as? BaseActivity<*, *>)?.showLoader()
    }

    override fun hideLoader() {
        (activity as? BaseActivity<*, *>)?.hideLoader()
    }

    override fun handleError(error: ProjectError) {
        (activity as? BaseActivity<*, *>)?.handleError(error)
    }

    override fun showErrorDialog(title: String, body: String) {
        (activity as? BaseActivity<*, *>)?.showErrorDialog(title, body)
    }

    override fun showDialog(
        title: CharSequence,
        body: CharSequence,
        posBtnText: CharSequence,
        negBtnText: CharSequence?,
        onPosBtnClicked: (() -> Unit)?,
        onNegBtnClicked: (() -> Unit)?,
        onDismiss: (() -> Unit)?
    ) {
        (activity as? BaseActivity<*, *>)?.showDialog(title, body, posBtnText, negBtnText, onPosBtnClicked, onNegBtnClicked, onDismiss)
    }

    override fun hideKeyboard() {
        (activity as? BaseActivity<*, *>)?.hideKeyboard()
    }

    override fun enableActionMenu(toolbar: Toolbar) {

    }

}