package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.mvvm_core.base.viewmodel.ErrorState
import cn.xpcheng.mvvm_core.base.viewmodel.LoadingState
import cn.xpcheng.mvvm_core.base.viewmodel.SuccessState
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.data.bean.PageInfo
import cn.xpcheng.wanadnroidmvvm.repository.ProjectChildRepository

/**
 *@author chengxinping
 *@time 2020/7/30
 *@desc
 */
class ProjectChildViewModel(private val projectChildRepository: ProjectChildRepository) :
    BaseViewModel() {

    //由于最新项目列表和项目列表分页不一样  最新项目从0开始   项目从1开始
    var page = 1

    var projectArticles = MutableLiveData<PageInfo<Article>>()


    fun getProjectList(isRefresh: Boolean, cid: Int, isNew: Boolean = false) {

        if (isRefresh)
            page = if (isNew) 0 else 1
        launch({ projectChildRepository.getProjectArticles(page, cid, isNew) },
            isShowLoading = false,
            isSpecialError = true,
            success = {
                page++
                projectArticles.postValue(it)
            })
    }
}