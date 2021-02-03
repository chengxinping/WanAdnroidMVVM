package cn.xpcheng.wanadnroidmvvm.ext

import androidx.fragment.app.Fragment
import cn.xpcheng.wanadnroidmvvm.R
import com.fengchen.uistatus.UiStatusController
import com.fengchen.uistatus.annotation.UiStatus

/**
 * @author ChengXinPing
 * @time   2021/2/2 11:55
 *
 */
fun Fragment.onReload(controller: UiStatusController, callback: () -> Unit) {
    controller.addUiStatusConfig(
        UiStatus.LOAD_ERROR, R.layout.layout_error, R.id.tv_retry
    ) { _, controller, _ ->
        controller.changeUiStatus(UiStatus.LOADING)
        callback.invoke()
    }
        .addUiStatusConfig(
            UiStatus.EMPTY,
            R.layout.layout_empty,
            R.id.tv_retry
        ) { _, controller, _ ->
            controller.changeUiStatus(UiStatus.LOADING)
            callback.invoke()
        }
}