package com.delicloud.app.mvvm_core.base.livedata

import androidx.lifecycle.MutableLiveData


/**
 * @author ChengXinPing
 * @time   2020/11/30 9:54
 * 描述　:自定义的Float类型 MutableLiveData 提供了默认值，避免取值的时候还要判空
 */
class FloatLiveData(value: Float = 0f) : MutableLiveData<Float>(value) {
    override fun getValue(): Float {
        return super.getValue()!!
    }
}