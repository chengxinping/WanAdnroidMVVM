package cn.xpcheng.wanadnroidmvvm.viewmodel

import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.repository.UploadArticleRepository
import com.delicloud.app.mvvm_core.base.livedata.BooleanLiveData

/**
 * @author ChengXinPing
 * @time   2021/2/20 16:27
 *
 */
class UploadArticleViewModel(private val repository: UploadArticleRepository) : BaseViewModel() {
    var isUploadSuccess = BooleanLiveData()

    fun uploadArticle(title: String, content: String) {
        launch({ repository.uploadArticle(title, content) },
            isShowLoading = true,
            isSpecialError = false, success = {
                isUploadSuccess.postValue(true)
            })
    }
}