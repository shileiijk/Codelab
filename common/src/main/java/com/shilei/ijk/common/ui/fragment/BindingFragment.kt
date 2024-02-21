package com.shilei.ijk.common.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.shilei.ijk.common.ui.fragment.support.BaseFragment
import timber.log.Timber

abstract class BindingFragment<T : ViewBinding> : BaseFragment() {
    protected lateinit var binding: T

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView")
        binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")
    }
}