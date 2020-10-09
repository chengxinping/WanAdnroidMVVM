package cn.xpcheng.wanadnroidmvvm.data.db

import androidx.room.*
import cn.xpcheng.wanadnroidmvvm.data.bean.SearchHistoryKey

/**
 * @author ChengXinPing
 * @time   2020/9/27 15:26
 *
 */
@Dao
interface SearchHistoryDao {

    @Query("select * from SearchHistoryKey")
    suspend fun searchAll(): MutableList<SearchHistoryKey>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchHistoryKey: SearchHistoryKey)

    @Query("delete from SearchHistoryKey")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(vararg searchHistoryKey: SearchHistoryKey)
}