package cn.xpcheng.wanadnroidmvvm.di

import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi
import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApiService
import cn.xpcheng.wanadnroidmvvm.repository.Main2Repository
import cn.xpcheng.wanadnroidmvvm.repository.MainRepository
import cn.xpcheng.wanadnroidmvvm.viewmodel.Main2ViewModel
import cn.xpcheng.wanadnroidmvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc
 */

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { Main2ViewModel(get()) }
}

val responseModule = module {
    //factory就是获取的时候每次都生成一个新的实例
    factory { MainRepository(get()) }
    factory { Main2Repository(get()) }
}

val apiModule = module {
    single<WanAndroidApi> { WanAndroidApiService }
}

val appModule = listOf(viewModelModule, responseModule, apiModule)

