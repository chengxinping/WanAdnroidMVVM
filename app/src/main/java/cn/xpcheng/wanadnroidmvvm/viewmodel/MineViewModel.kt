package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.Point
import cn.xpcheng.wanadnroidmvvm.repository.MineRepository
import com.delicloud.app.mvvm_core.base.livedata.BooleanLiveData

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class MineViewModel(private val repository: MineRepository) : BaseViewModel() {

    var isLogoutSuccess = BooleanLiveData()

    var point = MutableLiveData<Point>()

    fun logout() {
        launch({ repository.logout() }, isShowLoading = true, isSpecialError = false, success = {
            isLogoutSuccess.postValue(true)
        })
    }

    fun getMyPoint() {
        launch({ repository.getMyPoint() }, point, false)
    }
}