package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.ArticleBody
import cn.xpcheng.wanadnroidmvvm.repository.TreeArticleRepository

/**
 * @author ChengXinPing
 * @time   2021/1/6 15:18
 *
 */
class TreeArticleViewModel(private val mRepository: TreeArticleRepository) : BaseViewModel() {

    var article = MutableLiveData<ArticleBody>()

    fun getTreeArticle(page: Int, cid: Int) {
        launch({ mRepository.getTreeArticle(page, cid) }, article, true)
    }
}