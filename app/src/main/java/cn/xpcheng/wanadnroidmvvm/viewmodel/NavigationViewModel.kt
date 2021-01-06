 package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.NavigationBean
import cn.xpcheng.wanadnroidmvvm.repository.NavigationRepository

/**
 * @author ChengXinPing
 * @time   2020/11/19 15:33
 *
 */
class NavigationViewModel(private val navigationRepository: NavigationRepository) :
    BaseViewModel() {

    val navigationList = MutableLiveData<List<NavigationBean>>()

    fun getNavigationList() {
        launch({ navigationRepository.getNavigation() }, navigationList, true)
    }
}