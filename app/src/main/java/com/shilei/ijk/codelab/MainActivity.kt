package com.shilei.ijk.codelab

import com.shilei.ijk.codelab.databinding.ActivityMainBinding
import com.shilei.ijk.common.ui.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private var isEnabled = true

    override fun handleOnBackPress(): Boolean {
        return super.handleOnBackPress()
    }
}