package com.eostek.kotlinpractice.sqlite_demo

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.eostek.kotlinpractice.R
import com.eostek.kotlinpractice.tool.PermissionsTool
import kotlinx.android.synthetic.main.act_sql.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class SQliteDemoAct : AppCompatActivity() {
    /**数据库文件的路径*/
    var fileName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_sql)

        Log.e("SQliteDemoAct", "" + fileName)
        fileName = "$filesDir/ZPTest.db"
//        申请sd卡读写权限
        PermissionsTool(this).requestPermissions()
    }

    fun createdatabase(view: View) {
        /**创建数据表的SQL语句*/
        val createTableSQL = "CREATE TABLE [Article] (" +
                "[id] INTEGER PRIMARY KEY AUTOINCREMENT," +
                "[title] VARCHAR(50)," +
                "[author] VARCHAR(100)," +
                "[content] TEXT," +
                "[date] DATE," +
                "[status] VARCHAR(20)" +
                ")"
        //这里创建数据库的时候是强制创建，如果发现有旧的数据库，就先删除旧数据库，然后再创建
        val file = File(fileName)
        if (file.exists()) {
            //如果文件存在就删除旧文件
            file.delete()
        }
        /**数据库对象*/
        val database = SQLiteDatabase.openOrCreateDatabase(fileName, null)
        //建立数据库数据表
        database.execSQL(createTableSQL)
        database.close()
        Toast.makeText(this, "数据库" + fileName, Toast.LENGTH_LONG).show()
    }

    fun addData(view: View) {
        val database = SQLiteDatabase.openOrCreateDatabase(fileName, null)
        //第一种方式
        val contentValues = ContentValues()
        contentValues.put("title", "金龙翼")
        contentValues.put("author", "厚土火焰山")
        contentValues.put("content", "go和kotlin的技术公司，企业IT系统移动化解决方案。")
        contentValues.put("date", getNow())
        contentValues.put("status", "edit")
        database.insert("Article", null, contentValues)

        //第二种方式
//        val insertSQL = "insert into Article(title, author, content, date, status) values(?,?,?,?,?)"
//        database.execSQL(insertSQL, arrayOf("我的公司", "Smith", "是专业的软件网络公司，为企业提供IT技术支撑。", getNow(), "publish"))


        //重复添加3条
//        for (i in 1..3) {
//            val insertSQL = "insert into Article(title, author, content, date, status) values(?,?,?,?,?)"
//            database.execSQL(insertSQL, arrayOf("我的公司", "金龙翼", "是专业的软件网络公司，为企业提供IT技术支撑。", getNow(), "publish"))
//        }
        database.close()
        Toast.makeText(this, "写入数据完成", Toast.LENGTH_LONG).show()
    }

    fun searchData(view: View) {
        val database = SQLiteDatabase.openOrCreateDatabase(fileName, null)
        var queryResult = ""
        //使用query方法查询Article数据表中的记录
        /*val cursor = database.query(
            "Article", arrayOf("title", "author"),
            "title=?", arrayOf("我的公司"), "", "", ""
        )
        try {
            cursor.moveToFirst()
            queryResult += cursor.getString(0) + " -> " + cursor.getString(1) + "\r\n"
        } catch (e: Exception) {
            e.printStackTrace()
        }
        database.close()*/
        val querySQL = "select * from Article"
        val cursor2 = database.rawQuery(querySQL, null)
        try {
            cursor2.moveToFirst()
            while (!cursor2.isAfterLast) {
                queryResult += cursor2.getString(0) + " -> " + cursor2.getString(1) + " -> " + cursor2.getString(2) + " -> " + cursor2.getString(
                    3
                ) + " -> " + cursor2.getString(4) + " -> " + cursor2.getString(5) + "\r\n"
                cursor2.moveToNext()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        database.close()
        ttvwQueryResult.text = queryResult
    }

    fun changeData(view: View) {
        //修改数据有两方式
        val database = SQLiteDatabase.openOrCreateDatabase(fileName, null)
        //参数方式
        val contentValues = ContentValues()
        contentValues.put("content", "这是我修改的一段内存")
        database.update("Article", contentValues, "author=?", arrayOf("金龙翼"))
        //sql语句方式
//        val updateSQL = "update Article set content='这是我修改的内容2' where author=?"
//        database.execSQL(updateSQL, arrayOf("Smith"))
        database.close()
        Toast.makeText(this, "修改完成", Toast.LENGTH_LONG).show()
    }

    fun deleteData(view: View) {
        val database = SQLiteDatabase.openOrCreateDatabase(fileName, null)
        //参数全匹配
        database.delete("Article", "author=?", arrayOf("金龙翼"))
        //SQL语句条件等于,id大于2的都删除
//        val deleteSQLThan2 = "delete from Article where id>?"
//        database.execSQL(deleteSQLThan2, arrayOf("2"))
        //SQL语句条件范围
//        val deleteSql = "delete from Article where author=?"
//        database.execSQL(deleteSql, arrayOf("Smith"))

        Toast.makeText(this, "删除成功", Toast.LENGTH_LONG).show()
    }

    /**完成创建数据库和输入数据后，为了能在SqliteExpert中查看数据库文件。先完成数据库复制的功能（将db考到sd卡中）*/
    fun copyDatabase(view: View) {
        val fos = FileOutputStream("/sdcard/ZPTest.db")
        try {
            val inputStream = FileInputStream(fileName)
            //定义写入数据的缓存，每次写入100字节
            val b = byteArrayOf(100)
            var count = 0
            //循环写入文件数据
            while (true) {
                count = inputStream.read(b)
                if (count < 0) {
                    break
                }
                fos.write(b, 0, count)
            }
            fos.close()
            inputStream.close()
            Toast.makeText(this, "数据库复制保存成功", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    /**
     * 日期函数
     * 功能描述：返回当前日期，格式：2017-12-19 12:13:55.917
     */
    fun getNow(): String {
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date())
        } else {
            var tms = Calendar.getInstance()
            return tms.get(Calendar.YEAR).toString() + "-" + tms.get(Calendar.MONTH + 1).toString() + "-" +
                    tms.get(Calendar.DAY_OF_MONTH).toString() + " " + tms.get(Calendar.HOUR_OF_DAY).toString() +
                    ":" + tms.get(Calendar.MINUTE).toString() + ":" + tms.get(Calendar.SECOND).toString() + "." +
                    tms.get(Calendar.MILLISECOND).toString()
        }
    }
}