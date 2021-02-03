package cn.xpcheng.wanadnroidmvvm.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.jessyan.autosize.internal.CancelAdapt


/**
 * @author ChengXinPing
 * @time   2021/2/2 9:42
 *
 */
class SplashActivity : AppCompatActivity(), CancelAdapt {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.postDelayed({
            Intent(this, MainActivity::class.java).run {
                startActivity(this)
                finish()
            }
        }, 1000)
    }
}