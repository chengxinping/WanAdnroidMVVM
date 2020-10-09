package cn.xpcheng.wanadnroidmvvm.repository

import cn.xpcheng.wanadnroidmvvm.data.bean.BaseApiResponse
import cn.xpcheng.wanadnroidmvvm.data.bean.HotKey
import cn.xpcheng.wanadnroidmvvm.data.bean.SearchHistoryKey
import cn.xpcheng.wanadnroidmvvm.data.db.SearchHistoryDao
import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *@author chengxinping
 *@time 2020/9/14
 *@desc
 */
class SearchRepository(
    private val wanAndroidApi: WanAndroidApi,
    private val searchHistoryDao: SearchHistoryDao
) {
    suspend fun getHotKey(): BaseApiResponse<ArrayList<HotKey>> = wanAndroidApi.getHotKeys()

    //获取搜索历史
    suspend fun getHistorySearchKey(): MutableList<SearchHistoryKey> = searchHistoryDao.searchAll()

    //删除历史记录
    suspend fun deleteAllHistory() = searchHistoryDao.deleteAll()

    //插入历史记录
    suspend fun insertHistory(historyKey: SearchHistoryKey) = searchHistoryDao.insert(historyKey)

    //删除某条历史记录
    suspend fun deleteHistory(searchHistoryKey: SearchHistoryKey) =
        searchHistoryDao.delete(searchHistoryKey)
}