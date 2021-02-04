package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.ShareInfo
import cn.xpcheng.wanadnroidmvvm.repository.MyArticleRepository

/**
 * @author ChengXinPing
 * @time   2021/2/4 16:54
 *
 */
class MyArticleViewModel(private val repository: MyArticleRepository) : BaseViewModel() {

    var shareInfo = MutableLiveData<ShareInfo>()

    fun getMyArticle(page: Int) {
        launch({ repository.getMyArticle(page) }, shareInfo, false)
    }

}