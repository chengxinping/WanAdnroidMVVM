package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.data.bean.Collect
import cn.xpcheng.wanadnroidmvvm.data.bean.PageInfo
import cn.xpcheng.wanadnroidmvvm.repository.CollectArticleRepository

/**
 * @author ChengXinPing
 * @time   2021/2/4 15:25
 *
 */
class CollectArticleViewModel(private val repository: CollectArticleRepository) : BaseViewModel() {
    var collectArticle = MutableLiveData<PageInfo<Collect>>()

    fun getCollectArticle(page: Int) {
        launch({ repository.getCollectArticle(page) }, collectArticle, false)
    }
}