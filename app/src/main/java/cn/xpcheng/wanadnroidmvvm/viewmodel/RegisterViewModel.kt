package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.UserInfo
import cn.xpcheng.wanadnroidmvvm.repository.RegisterRepository

/**
 * @author ChengXinPing
 * @time   2021/1/27 11:54
 *
 */
class RegisterViewModel(private val repository: RegisterRepository) : BaseViewModel() {

    var userInfo = MutableLiveData<UserInfo>()

    fun registerAndLogin(
        userName: String,
        password: String,
        repassword: String
    ) {
        launch({ repository.registerAndLogin(userName, password, repassword) }, userInfo, true)
    }
}