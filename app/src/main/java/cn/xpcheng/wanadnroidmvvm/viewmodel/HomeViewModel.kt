package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.data.bean.PageInfo
import cn.xpcheng.wanadnroidmvvm.data.bean.Banner
import cn.xpcheng.wanadnroidmvvm.repository.HomeRepository

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */

class HomeViewModel(private val mHomeRepository: HomeRepository) : BaseViewModel() {
    var homeArticles = MutableLiveData<PageInfo<Article>>()

    var bannerData = MutableLiveData<List<Banner>>()

    fun getHomeData(page: Int) {
        launch({ mHomeRepository.getHomeData(page) }, homeArticles, false)
    }

    fun getBanner() {
        launch({ mHomeRepository.getBanner() }, bannerData, false)
    }

}