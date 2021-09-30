package com.nikhil.startertemplate.base

import androidx.appcompat.widget.Toolbar
import com.nikhil.startertemplate.common.models.ProjectError

interface BaseViewContract {
    fun showLoader()
    fun hideLoader()

    fun handleError(error: ProjectError)
    fun showErrorDialog(title: String, body: String)
    fun showDialog(
        title: CharSequence,
        body: CharSequence,
        posBtnText: CharSequence,
        negBtnText: CharSequence? = null,
        onPosBtnClicked: (() -> Unit)? = null,
        onNegBtnClicked: (() -> Unit)? = null,
        onDismiss: (() -> Unit)? = null
    )

    fun hideKeyboard()
}

interface BaseFragmentContract: BaseViewContract {
    fun enableActionMenu(toolbar: Toolbar)
}

interface BaseActivityContract: BaseViewContract {

}

