package com.shilei.ijk.wandroid

import android.os.Bundle
import android.os.PersistableBundle
import com.shilei.ijk.common.ui.BaseActivity
import com.shilei.ijk.wandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding.viewPager
    }
}