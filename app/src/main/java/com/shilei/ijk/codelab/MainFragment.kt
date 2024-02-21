package com.shilei.ijk.codelab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.shilei.ijk.codelab.databinding.FragmentMainBinding
import com.shilei.ijk.common.ui.fragment.BindingFragment
import com.shilei.ijk.common.ui.fragment.support.FragmentStackManager

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : BindingFragment<FragmentMainBinding>() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // commitNow() 立即提交，如果FragmentManager中已有未完成的任务会出现异常 IllegalStateException("FragmentManager is already executing transactions")
        // commit() 排队等待提交
        val menuFragment = MenuFragment.newInstance("", "")
        val fragmentOne = FragmentOne.newInstance("", "")
        requireActivity().supportFragmentManager.beginTransaction().apply {
            add(R.id.menu_fragment, menuFragment)
            setMaxLifecycle(menuFragment, Lifecycle.State.RESUMED)
            add(R.id.host_fragment, fragmentOne)
            setMaxLifecycle(fragmentOne, Lifecycle.State.RESUMED)
        }.commit()

        FragmentStackManager.hideHome.observe(requireActivity()) {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                if (it) {
                    hide(menuFragment)
                    hide(fragmentOne)
                    setMaxLifecycle(menuFragment, Lifecycle.State.STARTED)
                    setMaxLifecycle(fragmentOne, Lifecycle.State.STARTED)
                } else {
                    show(menuFragment)
                    show(fragmentOne)
                    setMaxLifecycle(menuFragment, Lifecycle.State.RESUMED)
                    setMaxLifecycle(fragmentOne, Lifecycle.State.RESUMED)
                }
            }.commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}