package com.shilei.ijk.common.ui.fragment.support

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.shilei.ijk.common.R
import timber.log.Timber

/**
 * 基于containerId的Fragment管理器, 当前仅适用于MainActivity的R.id.fullscreen_fragment
 * 所有需要添加到R.id.fullscreen_fragment容器中的Fragment否需要通过本类showFragment
 * 如果需要区分栈, 需要创建HashMap根据containerId进行管理
 */
object FragmentStackManager {
    val hideHome: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        value = false
    }

    private val fragmentStack = mutableListOf<BaseFragment>()

    private val fragmentMap = mutableMapOf<Int, ArrayList<BaseFragment>>()

    private var fragmentManager: FragmentManager? = null

    fun init(manager: FragmentManager) {
        fragmentManager = manager
    }

    fun showFragment(containerId: Int, fragment: BaseFragment, hideParent: Boolean = true) {
        Timber.d("showFragment: $fragment")
        var fragmentList = fragmentMap[containerId]
        if (fragmentList == null) {
            fragmentMap[containerId] = arrayListOf(fragment)
            fragmentList = fragmentMap[containerId]
        }

        fragmentManager?.let { fm ->
            val transaction = fm.beginTransaction()
            val findFragment = fm.findFragmentByTag(fragment::class.java.simpleName)
            if (findFragment == null) {
                Timber.d("showFragment: add fragment: $fragment")
                transaction.add(containerId, fragment, fragment::class.java.simpleName)
                if (fragmentStack.isNotEmpty()) {
                    val lastFragment = fragmentStack.last()
                    if (hideParent) {
                        transaction.hide(lastFragment)
                    }
                    transaction.setMaxLifecycle(lastFragment, Lifecycle.State.STARTED)
                    saveFragmentState(lastFragment)
                }
                fragmentStack.add(fragment)
                fragmentList?.add(fragment)

                transaction.addToBackStack(fragment::class.java.simpleName)
                transaction.commit()
            } else {
                findFragment.arguments = fragment.arguments
                Timber.d("showFragment: show findFragment: $findFragment")
                transaction.show(findFragment)
                transaction.setMaxLifecycle(findFragment, Lifecycle.State.RESUMED)
                transaction.commit()
            }
        }
        hideHome.value = true
        Timber.d("showFragment: stack size: ${fragmentStack.size}")
    }

    fun replaceFragment(containerId: Int, fragment: BaseFragment) {
        Timber.d("addFragment: $fragment")
        var fragmentList = fragmentMap[containerId]
        if (fragmentList == null) {
            fragmentMap[containerId] = arrayListOf()
            fragmentList = fragmentMap[containerId]
        }

        fragmentManager?.let { fm ->
            Timber.d("addFragment: add fragment: $fragment")
            val transaction = fm.beginTransaction()
            transaction.add(containerId, fragment, fragment::class.java.simpleName)
            if (fragmentStack.isNotEmpty()) {
                fragmentStack.removeLastOrNull()
                fragmentManager?.popBackStack()
            }
            fragmentStack.add(fragment)
            fragmentList?.add(fragment)

            transaction.addToBackStack(fragment::class.java.simpleName)
            transaction.commit()
        }

        hideHome.value = true
        Timber.d("showFragment: stack size: ${fragmentStack.size}")
    }

    fun popFragment() {
        Timber.d("popFragment: start, stack size: ${fragmentStack.size}")
        fragmentManager?.let { fm ->
            if (fragmentStack.isEmpty()) {
                Timber.d("popFragment: no fragment in stack")
                return
            }

            val fragment = fragmentStack.removeLastOrNull()
            Timber.d("popFragment: $fragment removed")
            fm.popBackStack()

            if (fragmentStack.isNotEmpty()) {
                val previousFragment = fragmentStack.last()
                restoreFragmentState(previousFragment)
                fm.beginTransaction().show(previousFragment).commit()
            } else {
                hideHome.value = false
            }
        }
        Timber.d("popFragment: end, stack size: ${fragmentStack.size}")
    }

    fun popToRoot() {
        fragmentManager?.let { fm ->
            while (fragmentStack.size > 1) {
                fragmentStack.removeAt(fragmentStack.size - 1)
                fm.popBackStackImmediate()
            }
            hideHome.value = false
        }
    }

    fun popToRoot(containerId: Int): Boolean {
        fragmentMap[containerId]?.apply {
            forEachIndexed { index, baseFragment ->
                Timber.d("popToRoot: fragment-$index: $baseFragment")
            }
            if (isNotEmpty()) {
                while (size > 0) {
                    removeLastOrNull()
                    val last = fragmentStack.removeLastOrNull()
                    Timber.d("popToRoot: remove fragment: $last")
                    fragmentManager?.popBackStackImmediate()
                }
                return true
            }
        }
        return false
    }

    fun getCurrentFragment(): IFragmentSupport? {
        val fragmentSupport = fragmentStack.lastOrNull()
        if (fragmentSupport == null) {
            Timber.w("getCurrentFragment: fragmentStack empty")
        }
        return fragmentSupport
    }

    fun stackNotEmpty() = fragmentStack.isNotEmpty()

    private fun saveFragmentState(lastFragment: BaseFragment) {
        lastFragment.view?.findFocus()?.let {
            lastFragment.view?.setTag(R.id.fragment_focused_view_id, it)
        }
    }

    private fun restoreFragmentState(previousFragment: BaseFragment) {
        val root = previousFragment.view
        val focus = root?.getTag(R.id.fragment_focused_view_id) as? View
        focus?.requestFocus()
    }

    fun clear() {
        fragmentStack.clear()
    }
}