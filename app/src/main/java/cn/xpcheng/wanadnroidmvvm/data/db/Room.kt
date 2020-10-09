package cn.xpcheng.wanadnroidmvvm.data.db

import androidx.room.Room
import androidx.room.RoomDatabase
import cn.xpcheng.wanadnroidmvvm.App

/**
 * @author ChengXinPing
 * @time   2020/9/27 15:44
 *
 */
private const val DB_NAME = "wan_android_db"

val room = Room.databaseBuilder(App.context, AppDataBase::class.java, DB_NAME)
    //Room的日志模式
    .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
    //数据库升级异常之后的回滚
    .fallbackToDestructiveMigration()
    //数据库升级
    //.addMigrations(sMigration)
    .build()

val searchHistoryDao = room.getSearchHistoryDao()


