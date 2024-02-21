package com.shilei.ijk.codelab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shilei.ijk.codelab.databinding.FragmentMenuBinding
import com.shilei.ijk.common.ui.fragment.BindingFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MenuFragment : BindingFragment<FragmentMenuBinding>() {
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
    ): FragmentMenuBinding {
       return FragmentMenuBinding.inflate(inflater, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}