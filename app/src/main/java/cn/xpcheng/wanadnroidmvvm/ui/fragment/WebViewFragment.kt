package cn.xpcheng.wanadnroidmvvm.ui.fragment

import android.text.Html
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.constant.Constant
import cn.xpcheng.wanadnroidmvvm.data.bean.WebViewData
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentWebviewBindingImpl
import cn.xpcheng.wanadnroidmvvm.ext.initClose
import cn.xpcheng.wanadnroidmvvm.ext.navigateBack
import cn.xpcheng.wanadnroidmvvm.viewmodel.WebViewViewModel
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.fragment_webview.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author chengxinping
 *@time 2020/8/21
 *@desc
 */

class  WebViewFragment : BaseFragment<WebViewViewModel, FragmentWebviewBindingImpl>() {
    override fun getLayoutId(): Int = R.layout.fragment_webview

    private val mViewModel: WebViewViewModel by viewModel()

    override fun getViewModel(): WebViewViewModel = mViewModel

    private var preWeb: AgentWeb.PreAgentWeb? = null
    private var mAgentWeb: AgentWeb? = null

    override fun initView() {
        setHasOptionsMenu(true)


        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBack()
                }
            })

        preWeb = AgentWeb.with(this)
            .setAgentWebParent(webview_container, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
    }

    override fun initData() {
        super.initData()
        arguments?.run {
            getParcelable<WebViewData>(Constant.KEY_WEB_VIEW_DATA)?.let {
                mViewModel.id = it.id
                mViewModel.isCollect = it.isCollect
                mViewModel.title = it.title
                mViewModel.url = it.url
            }
        }

        toolbar.run {
            mActivity.setSupportActionBar(this)
            initClose(Html.fromHtml(mViewModel.title).toString(), onBack = { onBack() })
        }
    }

    private fun onBack() {
        mAgentWeb?.let { web ->
            if (web.webCreator.webView.canGoBack()) {
                web.webCreator.webView.goBack()
            } else {
                navigateBack()
            }
        }
    }

    override fun lazyLoadData() {
        mAgentWeb = preWeb?.go(mViewModel.url)
    }

    override fun createObserver() {

    }

    override fun onPause() {
        mAgentWeb?.webLifeCycle?.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb?.webLifeCycle?.onDestroy()
        mActivity.setSupportActionBar(null)
        super.onDestroy()
    }

}