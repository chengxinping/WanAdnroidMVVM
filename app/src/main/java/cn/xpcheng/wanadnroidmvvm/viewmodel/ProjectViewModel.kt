package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.mvvm_core.base.viewmodel.ErrorState
import cn.xpcheng.mvvm_core.base.viewmodel.LoadingState
import cn.xpcheng.mvvm_core.base.viewmodel.SuccessState
import cn.xpcheng.wanadnroidmvvm.data.bean.TreeBean
import cn.xpcheng.wanadnroidmvvm.repository.ProjectRepository

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class ProjectViewModel(private val projectRepository: ProjectRepository) : BaseViewModel() {

    var projectTree: MutableLiveData<List<TreeBean>> = MutableLiveData()

    fun getProjectTree() {
//        launch({ projectRepository.getProjectTree() }, projectTree, false)
        mStateLiveData.postValue(LoadingState)
        launch({ projectRepository.getProjectTree() }, {
            mStateLiveData.postValue(SuccessState)
            val recentlyBean = TreeBean(mutableListOf(), -1, -1, "最新项目", -1, -1, false, -1)
            val toMutableList = it.toMutableList()
            toMutableList.add(0, recentlyBean)
            projectTree.postValue(toMutableList)
        }, {
            mStateLiveData.postValue(ErrorState(it))
        })
    }
}