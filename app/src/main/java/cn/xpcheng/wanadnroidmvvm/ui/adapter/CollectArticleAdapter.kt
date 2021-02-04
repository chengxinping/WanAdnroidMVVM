package cn.xpcheng.wanadnroidmvvm.ui.adapter

import android.text.Html
import android.widget.ImageView
import cn.xpcheng.wanadnroidmvvm.R
import cn.xpcheng.wanadnroidmvvm.data.bean.Collect
import coil.load
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author ChengXinPing
 * @time   2021/2/4 15:46
 *
 */
class CollectArticleAdapter(data: MutableList<Collect>) :
    BaseDelegateMultiAdapter<Collect, BaseViewHolder>(), LoadMoreModule {

    //文章类型
    private val ARTICLE = 1

    //项目类型
    private val PROJECT = 2

    init {
        // 第一步，设置代理
        setMultiTypeDelegate(object : BaseMultiTypeDelegate<Collect>() {
            override fun getItemType(data: List<Collect>, position: Int): Int {
                return if (data[position].envelopePic.isNotEmpty()) PROJECT else ARTICLE
            }
        })

        // 第二步，绑定 item 类型
        getMultiTypeDelegate()?.let {
            it.addItemType(ARTICLE, R.layout.item_home)
            it.addItemType(PROJECT, R.layout.item_project)
        }
    }

    override fun convert(holder: BaseViewHolder, item: Collect) {
        when (holder.itemViewType) {
            ARTICLE -> {
                //文章布局的赋值
                item.run {
                    holder.setText(R.id.tv_item_author, if (author.isEmpty()) "匿名用户" else author)
                        .setText(R.id.tv_item_title, Html.fromHtml(title))
                        .setText(R.id.tv_item_type, Html.fromHtml(chapterName))
                        .setText(R.id.tv_item_time, niceDate)
                        .setImageResource(R.id.iv_item_love, R.drawable.ic_red_love)
                        //隐藏所有标签
                        .setGone(R.id.tv_item_top, true)
                        .setGone(R.id.tv_item_new, true)
                        .setGone(R.id.tv_item_tag, true)
                }
            }

            PROJECT -> {
                item.run {
                    holder.setText(R.id.tv_item_author, if (author.isEmpty()) "匿名用户" else author)
                        .setText(R.id.tv_project_title, Html.fromHtml(title))
                        .setText(R.id.tv_project_type, Html.fromHtml(chapterName))
                        .setText(R.id.tv_item_time, niceDate)
                        .setImageResource(R.id.iv_project_love, R.drawable.ic_red_love)
                        .setText(R.id.tv_project_desc, Html.fromHtml(desc))
                        .getView<ImageView>(R.id.iv_project_img).load(envelopePic)

                }
            }
        }
    }
}