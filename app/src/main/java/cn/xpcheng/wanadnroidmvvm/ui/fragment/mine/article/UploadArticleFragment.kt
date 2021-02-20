package cn.xpcheng.wanadnroidmvvm.ui.fragment.mine.article

import android.text.Editable
import cn.xpcheng.common.utils.toast
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.base.BaseFragment
import cn.xpcheng.wanadnroidmvvm.databinding.FragmentUploadArticleBinding
import cn.xpcheng.wanadnroidmvvm.ext.initClose
import cn.xpcheng.wanadnroidmvvm.ext.navigateBack
import cn.xpcheng.wanadnroidmvvm.viewmodel.UploadArticleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author ChengXinPing
 * @time   2021/2/20 16:27
 *
 */
class UploadArticleFragment : BaseFragment<UploadArticleViewModel, FragmentUploadArticleBinding>() {

    private val mViewModel: UploadArticleViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_upload_article

    override fun getViewModel(): UploadArticleViewModel = mViewModel

    override fun initView() {
        mDataBinding.run {
            layoutToolbar.toolbar.initClose("发布文章") {
                navigateBack()
            }
            click = ProxyClick()
        }
    }

    override fun lazyLoadData() {

    }

    override fun createObserver() {
        mViewModel.isUploadSuccess.observe(viewLifecycleOwner, {
            if (it) {
                mDataBinding.etTitle.text = Editable.Factory.getInstance().newEditable("")
                mDataBinding.etContent.text = Editable.Factory.getInstance().newEditable("")
                "发布成功".toast(mActivity)
            }
        })
    }

    inner class ProxyClick {
        fun uploadArticle() {
            if (mDataBinding.etTitle.text.toString().trim()
                    .isNotEmpty() && mDataBinding.etContent.text.toString().isNotEmpty()
            ) {
                mViewModel.uploadArticle(
                    mDataBinding.etTitle.text.toString().trim(),
                    mDataBinding.etContent.text.toString().trim()
                )
            } else {
                "标题或文章链接不能为空".toast(mActivity)
            }
        }
    }
}