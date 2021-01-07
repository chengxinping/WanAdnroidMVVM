package com.delicloud.app.mvvm_core.base.livedata

import androidx.lifecycle.MutableLiveData


/**
 * @author ChengXinPing
 * @time   2020/11/30 9:54
 * 描述　:自定义的Short类型 MutableLiveData 提供了默认值，避免取值的时候还要判空
 */
class ShortLiveData : MutableLiveData<Short>() {
    override fun getValue(): Short {
        return super.getValue() ?: 0
    }
}