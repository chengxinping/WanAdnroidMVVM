package cn.xpcheng.wanadnroidmvvm.ui.activity

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import cn.xpcheng.mvvm_core.base.activity.BaseVmDbActivity
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.databinding.ActivityMainBinding
import cn.xpcheng.wanadnroidmvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseVmDbActivity<MainViewModel, ActivityMainBinding>() {

    private val mViewModel: MainViewModel by viewModel()

    override fun getViewModel(): MainViewModel = mViewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        btn.setOnClickListener {
            Intent(this, Main2Activity::class.java).run {
                startActivity(this)
            }
        }
    }

    override fun initData() {
        super.initData()
        mDataBinding.vm = mViewModel

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
