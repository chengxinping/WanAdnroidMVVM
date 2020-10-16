package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.ArticleBody
import cn.xpcheng.wanadnroidmvvm.data.bean.Banner
import cn.xpcheng.wanadnroidmvvm.repository.HomeRepository

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */

class HomeViewModel(private val mHomeRepository: HomeRepository) : BaseViewModel() {
    var homeArticles = MutableLiveData<ArticleBody>()

    var bannerData = MutableLiveData<ArrayList<Banner>>()

    fun getHomeData(page: Int) {
        launch({ mHomeRepository.getHomeData(page) }, homeArticles, true)
    }

    fun getBanner() {
        launch({ mHomeRepository.getBanner() }, bannerData, true)
    }

}