package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.UserInfo
import cn.xpcheng.wanadnroidmvvm.repository.LoginRepository

/**
 * @author ChengXinPing
 * @time   2021/1/25 14:19
 *
 */
class LoginViewModel(private val repository: LoginRepository) : BaseViewModel() {
    var userInfo = MutableLiveData<UserInfo>()

    fun login(userName: String, password: String) {
        launch({ repository.login(userName, password) }, userInfo, true)
    }
}