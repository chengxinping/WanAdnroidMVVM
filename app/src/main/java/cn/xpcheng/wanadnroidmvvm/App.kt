package cn.xpcheng.wanadnroidmvvm

import android.app.Application
import android.content.Context
import cn.xpcheng.wanadnroidmvvm.di.appModule
import com.fengchen.uistatus.UiStatusManager
import com.fengchen.uistatus.annotation.UiStatus
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.properties.Delegates

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc
 */

class App : Application() {

    companion object {
        var context: Context by Delegates.notNull()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        context = this
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }

        UiStatusManager.getInstance()
            .addUiStatusConfig(UiStatus.LOADING, R.layout.layout_loading)
    }
}