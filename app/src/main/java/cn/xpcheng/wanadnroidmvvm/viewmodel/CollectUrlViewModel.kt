package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.WebSite
import cn.xpcheng.wanadnroidmvvm.repository.CollectUrlRepository

/**
 * @author ChengXinPing
 * @time   2021/2/4 15:23
 *
 */
class CollectUrlViewModel(private val repository: CollectUrlRepository) : BaseViewModel() {

    var webSite = MutableLiveData<List<WebSite>>()

    fun getCollectUrl() {
        launch({ repository.getCollectUrl() }, webSite, false)
    }
}