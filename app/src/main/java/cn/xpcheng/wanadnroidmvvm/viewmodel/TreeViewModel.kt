package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.Tree
import cn.xpcheng.wanadnroidmvvm.repository.TreeRepository

/**
 * @author ChengXinPing
 * @time   2021/1/4 16:15
 *
 */
class TreeViewModel(private val mRepository: TreeRepository) : BaseViewModel() {

    val tree = MutableLiveData<List<Tree>>()

    fun getTree() {
        launch({ mRepository.getTree() }, tree, true)
    }
}