package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.data.bean.PageInfo
import cn.xpcheng.wanadnroidmvvm.repository.SquareListRepository

/**
 * @author ChengXinPing
 * @time   2020/11/19 10:12
 *
 */
class SquareListViewModel(private val squareListRepository: SquareListRepository) :
    BaseViewModel() {
    var squareList = MutableLiveData<PageInfo<Article>>()

    fun getSquareList(page: Int) {
        launch({ squareListRepository.getSquareList(page) }, squareList, false)
    }
}