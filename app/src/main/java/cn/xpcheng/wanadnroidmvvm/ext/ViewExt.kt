package cn.xpcheng.wanadnroidmvvm.ext

import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

/**
 *@author chengxinping
 *@time 2020/8/21
 *@desc
 */

fun Toolbar.init(title: String): Toolbar {
    this.title = title
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