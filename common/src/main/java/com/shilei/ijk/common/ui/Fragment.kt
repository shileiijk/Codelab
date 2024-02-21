package com.shilei.ijk.common.ui

import android.os.Bundle
import androidx.fragment.app.Fragment

fun Fragment.replaceFragment(fragmentContainerId: Int, fragmentClass: Class<out Fragment>, bundle: Bundle?) {
    val tag = fragmentClass.simpleName
    val fragmentManager = requireActivity().supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    val found = fragmentManager.findFragmentByTag(tag)
    if (found != null) {
        fragmentTransaction.replace(fragmentContainerId, found)
    } else {
        val fragment = fragmentClass.newInstance().apply {
            arguments = bundle
        }
        fragmentTransaction.replace(fragmentContainerId, fragment, tag)
        fragmentTransaction.addToBackStack(tag)
    }
    fragmentTransaction.commit()
}
