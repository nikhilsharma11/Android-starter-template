package com.nikhil.startertemplate.screens.host

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nikhil.startertemplate.R
import com.nikhil.startertemplate.base.BaseActivity
import com.nikhil.startertemplate.databinding.ActivityHostBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HostActivity : BaseActivity<HostEvent, HostViewModel>() {

    private var downTouchTimestamp: Long? = null
    private lateinit var binding: ActivityHostBinding
    override val viewModel: HostViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_host)

        val navController = supportFragmentManager.findFragmentById(R.id.nav_fragment_host)?.findNavController()

        viewModel.events.observe(this, Observer {
            when (it) {
                is HostEvent.GoToLanding -> {
                    finish()
                    startActivity(intent)
                }
            }
        })
    }

    /**
     * Handle clearing of focus for inner views when tapping outside of focusable areas
     */
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            downTouchTimestamp = System.currentTimeMillis()
        } else if (event.action == MotionEvent.ACTION_UP) {
            downTouchTimestamp?.let {
                val deltaTime = System.currentTimeMillis() - it
                if (deltaTime > TOUCH_CLICK_THRESHOLD_MILLIS) {
                    downTouchTimestamp = null
                    return super.dispatchTouchEvent(event)
                }
            }
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                    imm!!.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    companion object {
        private const val TOUCH_CLICK_THRESHOLD_MILLIS = 150
        @JvmStatic
        fun getIntent(context: Context) = Intent(context, HostActivity::class.java)
    }
}
