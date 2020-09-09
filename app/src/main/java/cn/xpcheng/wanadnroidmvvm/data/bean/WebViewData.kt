package cn.xpcheng.wanadnroidmvvm.data.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *@author chengxinping
 *@time 2020/9/9
 *@desc
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class WebViewData(
    val id: Int,
    val url: String,
    val title: String,
    var isCollect: Boolean = false
) : Parcelable