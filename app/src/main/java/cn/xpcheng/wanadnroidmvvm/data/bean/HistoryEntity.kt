package cn.xpcheng.wanadnroidmvvm.data.bean

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * @author ChengXinPing
 *
 * @time   2021/2/20 17:23
 *
 */
@Entity
data class HistoryEntity(
    @ColumnInfo(name="time") val time:Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "isCollect") val isCollect: Boolean
)