package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.PageInfo
import cn.xpcheng.wanadnroidmvvm.data.bean.Point
import cn.xpcheng.wanadnroidmvvm.repository.PointRepository

/**
 * @author ChengXinPing
 * @time   2021/2/1 14:10
 *
 */
class PointViewModel(private val repository: PointRepository) : BaseViewModel() {

    var rankList = MutableLiveData<PageInfo<Point>>()

    fun getRankList(page: Int) {
        launch({ repository.getRankList(page) }, rankList, false)
    }
}