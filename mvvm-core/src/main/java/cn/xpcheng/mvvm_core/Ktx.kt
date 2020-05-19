package cn.xpcheng.mvvm_core

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 *@author chengxinping
 *@time 2020/5/19
 *@desc module 下的contentProvider 提供application
 */
class Ktx : ContentProvider() {

    companion object {
        lateinit var app: Application
    }

    override fun onCreate(): Boolean {
        val application = context!!.applicationContext as Application
        app = application
        return true
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? = null


    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int = 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun getType(uri: Uri): String? = null


}