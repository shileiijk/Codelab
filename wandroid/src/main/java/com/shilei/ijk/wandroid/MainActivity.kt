package com.shilei.ijk.wandroid

import com.shilei.ijk.common.ui.BaseActivity
import com.shilei.ijk.wandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}