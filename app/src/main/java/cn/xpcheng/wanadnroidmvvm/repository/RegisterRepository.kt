package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.mvvm_core.base.network.AppException
import cn.xpcheng.wanadnroidmvvm.data.bean.BaseApiResponse
import cn.xpcheng.wanadnroidmvvm.data.bean.UserInfo
import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2021/1/25 9:08
 *
 */
class RegisterRepository(private val api: WanAndroidApi) {

    suspend fun registerAndLogin(
        userName: String,
        password: String,
        repassword: String
    ): BaseApiResponse<UserInfo> {
        val registerResponse = api.register(userName, password, repassword)
        if (registerResponse.isSuccess()) {
            return api.login(userName, password)
        } else {
            throw AppException(registerResponse.errorCode, registerResponse.errorMsg)
        }
    }

}