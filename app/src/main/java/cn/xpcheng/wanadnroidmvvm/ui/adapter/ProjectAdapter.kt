package cn.xpcheng.wanadnroidmvvm.ui.adapter

import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.databinding.ItemProjectBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 * @author ChengXinPing
 * @time   2020/10/16 15:20
 *
 */
class ProjectAdapter(data: MutableList<Article>) :
    BaseQuickAdapter<Article, BaseDataBindingHolder<ItemProjectBinding>>(
        R.layout.item_project,
        data
    ),
    LoadMoreModule {
    override fun convert(holder: BaseDataBindingHolder<ItemProjectBinding>, item: Article) {
        val chapterName: String = when {
            item.superChapterName.isNotEmpty() and item.chapterName.isNotEmpty() ->
                "${item.superChapterName} / ${item.chapterName}"
            item.superChapterName.isNotEmpty() -> item.superChapterName
            item.chapterName.isNotEmpty() -> item.chapterName
            else -> ""
        }

        holder.dataBinding?.let {
            it.article = item
            it.tvProjectType.text = chapterName
            it.executePendingBindings()
        }
    }

}