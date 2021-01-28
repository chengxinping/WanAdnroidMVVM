package cn.xpcheng.common.utils;

import android.content.Context
import android.widget.Toast

/**
 * @author ChengXinPing
 * @time 2020/11/27 10:41
 */

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}
