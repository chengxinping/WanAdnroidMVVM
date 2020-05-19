package cn.xpcheng.mvvm_core.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *@author chengxinping
 *@time 2020/5/18
 *@desc 简单展示页面所用的BaseActivity
 */

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout()
    }

    open fun setContentLayout() {
        setContentView(getLayoutId())
        initView()
        initData()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    open fun initData() {

    }
}