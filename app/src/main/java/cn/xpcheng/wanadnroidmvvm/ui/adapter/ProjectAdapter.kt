package cn.xpcheng.wanadnroidmvvm.ui.adapter

import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.Article
import cn.xpcheng.wanadnroidmvvm.databinding.ItemProjectBindingImpl
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import kotlinx.android.synthetic.main.item_project.view.*

/**
 * @author ChengXinPing
 * @time   2020/10/16 15:20
 *
 */
class ProjectAdapter(data: MutableList<Article>) :
    BaseQuickAdapter<Article, BaseDataBindingHolder<ItemProjectBindingImpl>>(
        R.layout.item_project,
        data
    ),
    LoadMoreModule {
    override fun convert(holder: BaseDataBindingHolder<ItemProjectBindingImpl>, item: Article) {
        val chapterName: String = when {
            item.superChapterName.isNotEmpty() and item.chapterName.isNotEmpty() ->
                "${item.superChapterName} / ${item.chapterName}"
            item.superChapterName.isNotEmpty() -> item.superChapterName
            item.chapterName.isNotEmpty() -> item.chapterName
            else -> ""
        }
        holder.itemView.tv_project_type.text = chapterName
        holder.dataBinding?.let {
            it.article = item
            it.executePendingBindings()
        }
    }

}