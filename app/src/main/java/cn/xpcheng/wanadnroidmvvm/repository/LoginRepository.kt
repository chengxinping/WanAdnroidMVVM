package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2021/1/25 9:08
 *
 */
class LoginRepository(private val api: WanAndroidApi) {
    suspend fun login(userName: String, password: String) = api.login(userName, password)
}