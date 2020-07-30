package cn.xpcheng.wanadnroidmvvm.di

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi
import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApiService
import cn.xpcheng.wanadnroidmvvm.repository.MainRepository
import cn.xpcheng.wanadnroidmvvm.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc
 */

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { ProjectViewModel() }
    viewModel { SquareViewModel() }
    viewModel { WechatViewModel() }
    viewModel { MineViewModel() }
}

val responseModule = module {
    //factory就是获取的时候每次都生成一个新的实例
    factory { MainRepository(get()) }
}

val apiModule = module {
    single<WanAndroidApi> { WanAndroidApiService }
}

val appModule = listOf(viewModelModule, responseModule, apiModule)

