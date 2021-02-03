package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.data.bean.PageInfo
import cn.xpcheng.wanadnroidmvvm.repository.WxChildRepository

/**
 * @author ChengXinPing
 * @time   2020/10/14 16:09
 *
 */
class WxChildViewModel(private val wxChildRepository: WxChildRepository) :
    BaseViewModel() {

    var wxArticle = MutableLiveData<PageInfo<Article>>()

    fun getWxArticle(cid: Int, page: Int) {
        launch({ wxChildRepository.getWxArticle(cid, page) }, wxArticle, false)
    }
}