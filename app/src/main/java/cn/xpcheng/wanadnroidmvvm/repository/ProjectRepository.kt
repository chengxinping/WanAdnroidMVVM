package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2020/10/15 9:34
 *
 */
class ProjectRepository(private val wanAndroidApi: WanAndroidApi) {
    suspend fun getProjectTree() = wanAndroidApi.getProjectTree()
}