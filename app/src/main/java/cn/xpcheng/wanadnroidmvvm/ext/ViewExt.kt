package cn.xpcheng.wanadnroidmvvm.ext

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import cn.xpcheng.wanadnroidmvvm.R

/**
 *@author chengxinping
 *@time 2020/8/21
 *@desc
 */

fun Toolbar.init(title: String): Toolbar {
    this.title = title
    return this
}

fun Toolbar.initClose(
    titleStr: String = "",
    backImg: Int = R.drawable.ic_arrow_back,
    onBack: (toolbar: Toolbar) -> Unit
): Toolbar {
    title = titleStr
    setNavigationIcon(backImg)
    setNavigationOnClickListener {
        onBack.invoke(this)
    }
    return this
}


fun RecyclerView.init(
    mLayoutManager: RecyclerView.LayoutManager,
    mAdapter: RecyclerView.Adapter<*>
): RecyclerView {
    layoutManager = mLayoutManager
    adapter = mAdapter
    itemAnimator = DefaultItemAnimator()
    return this
}

/**
 * 隐藏软键盘
 */
fun hideSoftKeyboard(activity: Activity?) {
    activity?.let { act ->
        val view = act.currentFocus
        view?.let {
            val inputMethodManager =
                act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}