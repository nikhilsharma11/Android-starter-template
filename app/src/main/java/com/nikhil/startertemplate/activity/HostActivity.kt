package com.nikhil.startertemplate.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nikhil.startertemplate.R
import com.nikhil.startertemplate.base.BaseActivity
import com.nikhil.startertemplate.databinding.ActivityHostBinding
import com.nikhil.startertemplate.screens.host.HostEvent
import com.nikhil.startertemplate.screens.host.HostViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HostActivity : BaseActivity<HostEvent, HostViewModel>() {
    override val viewModel: HostViewModel by viewModel()

    private var downTouchTimestamp: Long? = null
    private lateinit var binding: ActivityHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_host)

        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_fragment_host)?.findNavController()

        viewModel.events.observe(this, Observer {
            when (it) {
                is HostEvent.GoToLanding -> {
                    finish()
                    startActivity(intent)
                }
                is HostEvent.GoToSetSimulator -> navController?.navigate(R.id.action_global_fragment_landing)
            }
        })
    }

    companion object {
        private const val TOUCH_CLICK_THRESHOLD_MILLIS = 150
        @JvmStatic
        fun getIntent(context: Context) = Intent(context, HostActivity::class.java)
    }
}