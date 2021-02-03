package cn.xpcheng.wanadnroidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.data.bean.PageInfo
import cn.xpcheng.wanadnroidmvvm.repository.QuestionRepository

/**
 * @author ChengXinPing
 * @time   2020/11/19 10:12
 *
 */
class QuestionViewModel(private val questionRepository: QuestionRepository) : BaseViewModel() {
    var questionList = MutableLiveData<PageInfo<Article>>()

    fun getQuestionList(page: Int) {
        launch({ questionRepository.getQuestionList(page) }, questionList, false)
    }
}