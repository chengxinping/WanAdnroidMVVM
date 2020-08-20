package cn.xpcheng.wanadnroidmvvm.ui.adapter

import androidx.databinding.DataBindingUtil
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.databinding.ItemHomeBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 *@author chengxinping
 *@time 2020/8/18
 *@desc
 */
class HomeAdapter(data: MutableList<Article>) :
    BaseQuickAdapter<Article, BaseDataBindingHolder<ItemHomeBinding>>(R.layout.item_home, data),
    LoadMoreModule {
    override fun convert(holder: BaseDataBindingHolder<ItemHomeBinding>, item: Article) {
        val chapterName: String = when {
            item.superChapterName.isNotEmpty() and item.chapterName.isNotEmpty() ->
                "${item.superChapterName} / ${item.chapterName}"
            item.superChapterName.isNotEmpty() -> item.superChapterName
            item.chapterName.isNotEmpty() -> item.chapterName
            else -> ""
        }

        holder.dataBinding?.let {
            it.article = item
            it.tvItemType.text = chapterName
            it.executePendingBindings()
        }
    }


}