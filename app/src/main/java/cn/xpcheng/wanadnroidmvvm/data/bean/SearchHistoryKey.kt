package cn.xpcheng.wanadnroidmvvm.data.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @author ChengXinPing
 * @time   2020/9/27 15:22
 *
 */
@Entity
data class SearchHistoryKey(
    @PrimaryKey @ColumnInfo(name = "history") val history: String
)
