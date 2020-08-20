package cn.xpcheng.wanadnroidmvvm.ui.activity

import cn.xpcheng.mvvm_core.base.activity.BaseVmDbActivity
import cn.xpcheng.mvvm_core.ext.parseStatusData
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.databinding.ActivityMainBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseVmDbActivity<MainViewModel, ActivityMainBinding>() {

    private val mViewModel: MainViewModel by viewModel()

    override fun getViewModel(): MainViewModel = mViewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {

    }

    override fun createObserver() {

    }

}
