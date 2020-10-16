package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.TreeBean
import cn.xpcheng.wanadnroidmvvm.repository.WxRepository

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class WxViewModel(private val wxRepository: WxRepository) : BaseViewModel() {
    var wxTree = MutableLiveData<List<TreeBean>>()

    fun getWxTree() {
        launch({ wxRepository.getWxtTree() }, wxTree, false)
    }
}