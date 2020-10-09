package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.ArticleBody
import cn.xpcheng.wanadnroidmvvm.repository.SearchDetailRepository

/**
 * @author ChengXinPing
 * @time   2020/10/9 9:48
 *
 */
class SearchDetailViewModel(private val searchDetailRepository: SearchDetailRepository) :
    BaseViewModel() {
    var searchResult = MutableLiveData<ArticleBody>()

    fun search(key: String, page: Int) {
        launch({ searchDetailRepository.search(key, page) }, searchResult, true)
    }
}