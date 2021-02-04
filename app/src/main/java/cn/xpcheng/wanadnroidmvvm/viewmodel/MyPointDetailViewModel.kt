package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.PageInfo
import cn.xpcheng.wanadnroidmvvm.data.bean.PointDetail
import cn.xpcheng.wanadnroidmvvm.repository.MyPointDetailRepository

/**
 * @author ChengXinPing
 * @time   2021/2/3 17:05
 *
 */
class MyPointDetailViewModel(private val repository: MyPointDetailRepository) : BaseViewModel() {

    var pointDetail = MutableLiveData<PageInfo<PointDetail>>()

    fun getMyPointDetail(page: Int) {
        launch({ repository.getMyPointDetail(page) }, pointDetail, false)
    }
}