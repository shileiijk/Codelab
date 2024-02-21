package com.shilei.ijk.codelab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shilei.ijk.codelab.databinding.FragmentTwoBinding
import com.shilei.ijk.common.ui.fragment.BindingFragment
import com.shilei.ijk.common.ui.fragment.support.FragmentStackManager
import com.shilei.ijk.common.util.onClick

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentTwo : BindingFragment<FragmentTwoBinding>() {
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
    ): FragmentTwoBinding {
        return FragmentTwoBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.jumpToPlayer.onClick {
            FragmentStackManager.showFragment(
                R.id.fragment_root,
                FragmentThree.newInstance("", "")
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTwo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}