package cn.xpcheng.wanadnroidmvvm.di

import cn.xpcheng.wanadnroidmvvm.data.db.searchHistoryDao
import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApi
import cn.xpcheng.wanadnroidmvvm.data.http.WanAndroidApiService
import cn.xpcheng.wanadnroidmvvm.repository.*
import cn.xpcheng.wanadnroidmvvm.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc
 */

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { ProjectViewModel() }
    viewModel { SquareViewModel() }
    viewModel { WxViewModel(get()) }
    viewModel { MineViewModel() }
    viewModel { WebViewViewModel() }
    viewModel { SearchViewModel(get()) }
    viewModel { SearchDetailViewModel(get()) }
    viewModel { WxChildViewModel(get()) }
}

val responseModule = module {
    //factory就是获取的时候每次都生成一个新的实例
    factory { HomeRepository(get()) }
    factory { SearchRepository(get(), get()) }
    factory { SearchDetailRepository(get()) }
    factory { WxRepository(get()) }
    factory { WxChildRepository(get()) }
}

val apiModule = module {
    single<WanAndroidApi> { WanAndroidApiService }
}

val localModule = module {
    single {
        searchHistoryDao
    }
}

val appModule = listOf(viewModelModule, responseModule, apiModule, localModule)

