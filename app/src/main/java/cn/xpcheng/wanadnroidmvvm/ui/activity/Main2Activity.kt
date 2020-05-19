package cn.xpcheng.wanadnroidmvvm.ui.activity

import android.util.Log
import androidx.lifecycle.Observer
import cn.xpcheng.mvvm_core.base.activity.BaseVmDbActivity
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.databinding.ActivityMainBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.Main2ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class Main2Activity : BaseVmDbActivity<Main2ViewModel, ActivityMainBinding>() {

    private val mViewModel: Main2ViewModel by viewModel()

    override fun getViewModel(): Main2ViewModel = mViewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {

    }

    override fun initData() {
        super.initData()
        mViewModel.getBanner()
    }


    override fun createObserver() {
        mViewModel.banners.observe(this, Observer {
            it?.let {
                Log.e("cxp", "数据变化")
            }
        })
    }

    override fun showLoading() {
        super.showLoading()
        Log.e("cxp", "loading")
    }

    override fun hideLoading() {
        super.hideLoading()
        Log.e("cxp", "hide")
    }

    override fun handlerError(errorMsg: String) {
        super.handlerError(errorMsg)
    }

}
