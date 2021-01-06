package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi

/**
 * @author ChengXinPing
 * @time   2020/11/19 10:10
 *
 */
class QuestionRepository(private val wanAndroidApi: WanAndroidApi) {
    suspend fun getQuestionList(page: Int) = wanAndroidApi.getQuestionList(page)
}