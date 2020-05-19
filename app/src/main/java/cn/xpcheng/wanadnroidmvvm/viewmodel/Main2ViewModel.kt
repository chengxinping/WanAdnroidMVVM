package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.Banner
import cn.xpcheng.wanadnroidmvvm.repository.Main2Repository

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc
 */
class Main2ViewModel(private val mMainRepository: Main2Repository) : BaseViewModel() {
    var banners = MutableLiveData<ArrayList<Banner>>()

    fun getBanner() {
        launch({ mMainRepository.getBanner() }, banners, true)
    }
}