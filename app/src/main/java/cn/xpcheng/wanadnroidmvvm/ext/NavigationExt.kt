package cn.xpcheng.wanadnroidmvvm.ext

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import cn.xpcheng.wanadnroidmvvm.navigation.NavHostFragment

/**
 *@author chengxinping
 *@time 2020/9/8
 *@desc
 */

fun Fragment.nav(@IdRes resId: Int, bundle: Bundle? = null) {
    NavHostFragment.findNavController(this).navigate(resId, bundle)
}

fun Fragment.nav(action: NavDirections) {
    NavHostFragment.findNavController(this).navigate(action)
}

fun Fragment.navigateBack() {
    NavHostFragment.findNavController(this).navigateUp()
}