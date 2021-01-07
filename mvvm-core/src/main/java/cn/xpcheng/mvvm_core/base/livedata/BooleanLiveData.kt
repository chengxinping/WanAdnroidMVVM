package com.delicloud.app.mvvm_core.base.livedata

import androidx.lifecycle.MutableLiveData

/**
 * @author ChengXinPing
 * @time   2020/11/30 9:54
 *自定义的Boolean类型 MutableLiveData 提供了默认值，避免取值的时候还要判空
 */
class BooleanLiveData : MutableLiveData<Boolean>() {

    override fun getValue(): Boolean {
        return super.getValue() ?: false
    }
}