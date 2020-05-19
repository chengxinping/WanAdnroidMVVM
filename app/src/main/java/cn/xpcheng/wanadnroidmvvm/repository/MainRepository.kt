package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.bean.Banner
import cn.xpcheng.wanadnroidmvvm.data.bean.BaseApiResponse
import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc 提供数据给viewModel数据  网络数据或者本地数据（sqlite、sp等）
 */
class MainRepository(private val wanAndroidApi: WanAndroidApi) {

    suspend fun getBanner(): BaseApiResponse<ArrayList<Banner>> = wanAndroidApi.getBanner()
}