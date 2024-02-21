package com.shilei.ijk.codelab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shilei.ijk.codelab.databinding.FragmentPlayerBinding
import com.shilei.ijk.common.ui.fragment.BindingFragment
import com.shilei.ijk.common.ui.fragment.support.FragmentStackManager
import com.shilei.ijk.common.util.onClick

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentThree : BindingFragment<FragmentPlayerBinding>() {
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
    ): FragmentPlayerBinding {
        return FragmentPlayerBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.jumpToSelf.onClick {
            FragmentStackManager.replaceFragment(R.id.fragment_root, newInstance("", ""))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentThree().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}