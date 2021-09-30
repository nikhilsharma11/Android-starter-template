package com.nikhil.startertemplate.base

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.nikhil.startertemplate.R
import com.nikhil.startertemplate.common.models.ProjectError
import com.nikhil.startertemplate.common.models.ProjectErrorJsonAdapter
import org.koin.android.ext.android.inject

abstract class BaseActivity<E : BaseEvent, VM: BaseViewModel<E>> : AppCompatActivity(), BaseActivityContract {

    private lateinit var loader: ProgressDialog
    private lateinit var errorDialog: AlertDialog
    private lateinit var dialog: AlertDialog

    private val projectErrorJsonAdapter: ProjectErrorJsonAdapter by inject()

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loaderVisibility.observe(this, Observer {
            when (it) {
                true -> showLoader()
                false -> hideLoader()
            }
        })
        viewModel.apiError.observe(this, Observer {
            handleError(it)
        })
    }

    override fun showLoader() {
        if (::loader.isInitialized) {
            loader.show()
        } else {
            loader = ProgressDialog(this).also {
                it.setCancelable(false)
                if (!it.isShowing) {
                    it.show()
                }
            }
        }
    }

    override fun hideLoader() {
        if (::loader.isInitialized) {
            loader.hide()
        }
    }

    override fun handleError(error: ProjectError) {
        showErrorDialog(error.title, error.detail ?: "")
    }

    override fun showErrorDialog(title: String, body: String) {
        if (::errorDialog.isInitialized) {
            errorDialog.apply {
                setTitle(title)
                setMessage(body)
            }
        } else {
            errorDialog = AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(body)
                .setCancelable(false)
                .setPositiveButton(R.string.error_dialog_btn_text) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
        }
        errorDialog.show()
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
        if (::dialog.isInitialized) dialog.dismiss()

        dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(body)
            .setCancelable(false)
            .setPositiveButton(posBtnText) { dialog, _ ->
                dialog.dismiss()
                onPosBtnClicked?.invoke()
            }
            .apply {
                negBtnText?.let {
                    setNegativeButton(it) { dialog, _ ->
                        dialog.dismiss()
                        onNegBtnClicked?.invoke()
                    }
                }
                setOnDismissListener {
                    onDismiss?.invoke()
                }
            }
            .create()
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::loader.isInitialized) {
            loader.dismiss()
        }
        if (::errorDialog.isInitialized) {
            errorDialog.dismiss()
        }
    }

    override fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun finish() {
        super.finish()
    }
}