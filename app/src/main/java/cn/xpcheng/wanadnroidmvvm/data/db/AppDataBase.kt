package cn.xpcheng.wanadnroidmvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import cn.xpcheng.wanadnroidmvvm.data.bean.SearchHistoryKey

/**
 * @author ChengXinPing
 * @time   2020/9/27 15:36
 *
 */
@Database(entities = [SearchHistoryKey::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getSearchHistoryDao(): SearchHistoryDao
}