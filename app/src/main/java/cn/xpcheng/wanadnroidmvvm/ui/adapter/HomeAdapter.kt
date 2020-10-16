package cn.xpcheng.wanadnroidmvvm.ui.adapter

import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.databinding.ItemHomeBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import kotlinx.android.synthetic.main.item_home.view.*

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
        holder.itemView.tv_item_type.text = chapterName
        holder.dataBinding?.let {
            it.article = item
            it.executePendingBindings()
        }
    }


}