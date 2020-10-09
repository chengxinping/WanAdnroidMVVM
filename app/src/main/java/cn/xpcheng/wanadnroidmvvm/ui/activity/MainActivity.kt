package cn.xpcheng.wanadnroidmvvm.ui.activity

import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseActivity
import cn.xpcheng.wanadnroidmvvm.databinding.ActivityMainBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private val mViewModel: MainViewModel by viewModel()

    override fun getViewModel(): MainViewModel = mViewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {

    }

    override fun createObserver() {

    }

}
