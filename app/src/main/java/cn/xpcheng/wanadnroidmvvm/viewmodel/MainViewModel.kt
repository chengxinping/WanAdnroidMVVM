package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.UserInfo
import cn.xpcheng.wanadnroidmvvm.utils.CacheUtil

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc
 */
class MainViewModel : BaseViewModel() {
    var userInfo = MutableLiveData<UserInfo>()

    init {
        userInfo.value = CacheUtil.getUserInfo()
    }
}