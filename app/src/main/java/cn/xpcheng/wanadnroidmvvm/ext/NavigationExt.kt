package cn.xpcheng.wanadnroidmvvm.ext

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.navigation.NavHostFragment
import cn.xpcheng.wanadnroidmvvm.utils.CacheUtil

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

fun Fragment.navOrLogin(@IdRes resId: Int, bundle: Bundle? = null) {
    if (CacheUtil.isLogin()) {
        this.nav(resId, bundle)
    } else {
        this.nav(R.id.action_global_loginFragment)
    }
}

fun Fragment.navOrLogin(action: NavDirections) {
    if (CacheUtil.isLogin()) {
        this.nav(action)
    } else {
        this.nav(R.id.action_global_loginFragment)
    }
}