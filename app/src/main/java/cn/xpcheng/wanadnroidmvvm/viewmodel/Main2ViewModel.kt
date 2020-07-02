package cn.xpcheng.wanadnroidmvvm.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.xpcheng.mvvm_core.base.viewmodel.BaseViewModel
import cn.xpcheng.wanadnroidmvvm.App
import cn.xpcheng.wanadnroidmvvm.data.bean.Banner
import cn.xpcheng.wanadnroidmvvm.repository.Main2Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc
 */
class Main2ViewModel(private val mMainRepository: Main2Repository) : BaseViewModel() {
    var banners = MutableLiveData<ArrayList<Banner>>()

    fun getBanner() {
        launch({ mMainRepository.getBanner() }, {
            Toast.makeText(App.context, it.size.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}