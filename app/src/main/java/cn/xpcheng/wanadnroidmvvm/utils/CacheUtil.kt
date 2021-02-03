package cn.xpcheng.wanadnroidmvvm.utils

import cn.xpcheng.wanadnroidmvvm.data.bean.UserInfo
import com.tencent.mmkv.MMKV

/**
 * @author ChengXinPing
 * @time   2021/1/27 16:17
 *
 */
object CacheUtil {
    fun saveUserInfo(userInfo: UserInfo?) {
        val kv = MMKV.defaultMMKV()!!
        kv.encode("user", userInfo)
        userInfo?.let {
            setIsLogin(true)
        } ?: setIsLogin(false)
    }

    fun getUserInfo(): UserInfo? {
        val kv = MMKV.defaultMMKV()!!
        return kv.decodeParcelable("user", UserInfo::class.java)
    }

    /**
     * 是否已经登录
     */
    fun isLogin(): Boolean {
        val kv = MMKV.defaultMMKV()!!
        return kv.decodeBool("login", false)
    }

    /**
     * 设置是否已经登录
     */
    private fun setIsLogin(isLogin: Boolean) {
        val kv = MMKV.defaultMMKV()!!
        kv.encode("login", isLogin)
    }
}