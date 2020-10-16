package cn.xpcheng.wanadnroidmvvm.ext

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import cn.xpcheng.wanadnroidmvvm.R
import coil.load

/**
 * @author ChengXinPing
 * @time   2020/10/16 15:24
 *自定义 BindingAdapter 配合databinding
 */

object CustomBindAdapter {

    /**
     * 给imageview添加 url 绑定
     */
    @SuppressLint("ResourceAsColor")
    @BindingAdapter(value = ["url"])
    @JvmStatic
    fun url(imageView: ImageView, url: String) {
        imageView.load(url) {
            crossfade(true)
            placeholder(
                ColorDrawable(
                    R.color.Grey300
                )
            )
        }
    }
}
