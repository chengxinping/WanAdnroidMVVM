package cn.xpcheng.wanadnroidmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.HotKey
import cn.xpcheng.wanadnroidmvvm.data.bean.SearchHistoryKey
import cn.xpcheng.wanadnroidmvvm.repository.SearchRepository

/**
 *@author chengxinping
 *@time 2020/9/14
 *@desc
 */
class SearchViewModel(private val searchRepository: SearchRepository) : BaseViewModel() {
    var hotKeys = MutableLiveData<List<HotKey>>()

    var historyKeys = MutableLiveData<MutableList<SearchHistoryKey>>()

    fun getHotKey() {
        launch({ searchRepository.getHotKey() }, hotKeys, false)
    }

    fun getHistory() {
        emit({ searchRepository.getHistorySearchKey() }, {
            it.reverse()
            historyKeys.postValue(it)
        })
    }

    fun deleteAllHistory() {
        emit({ searchRepository.deleteAllHistory() }, {
            historyKeys.postValue(arrayListOf())
        })
    }

    fun insertHistory(historyKey: SearchHistoryKey) {
        emit({ searchRepository.insertHistory(historyKey) }, {}, {})
    }

    fun deleteHistory(searchHistoryKey: SearchHistoryKey) {
        val value = historyKeys.value!!
        emit({ searchRepository.deleteHistory(searchHistoryKey) }, {
            value.remove(searchHistoryKey)
            historyKeys.postValue(value)
        })
    }
}