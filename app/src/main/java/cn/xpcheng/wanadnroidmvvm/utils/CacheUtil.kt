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
    }

    fun getUserInfo(): UserInfo? {
        val kv = MMKV.defaultMMKV()!!
        return kv.decodeParcelable("user", UserInfo::class.java)
    }


}