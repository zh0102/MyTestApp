package com.zh.ktapp.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.zh.ktapp.bean.Student
import com.zh.ktapp.bean.WeatherDetail
import com.zh.ktapp.bean.dao.StudentDao
import com.zh.ktapp.bean.dao.WeatherDetailDao

/**
 * @ClassName: DBManager
 * @Description: 数据库管理类
 * @Author: ZHW
 * @Date: 2022/10/13 上午11:52
 */

@Database(entities = [Student::class,WeatherDetail::class], version = 1, exportSchema = false)
abstract class DBManager : RoomDatabase() {

     abstract fun getWeatherDetailDao(): WeatherDetailDao
    abstract fun getStudentDao(): StudentDao

    companion object {
        private var dbManager: DBManager? = null
        private const val DB_NAME = "my.db"

        fun getInstance(context: Context): DBManager {
            return dbManager ?: synchronized(this) {
                dbManager ?: buildDatabase(context).also { dbManager = it }
            }
        }

        private fun buildDatabase(context: Context): DBManager {
            return Room.databaseBuilder(context, DBManager::class.java, DB_NAME)
                // .addMigrations(MIGRATION_1_2)
                .addCallback(CreatedCallBack)
                .allowMainThreadQueries()
                .build()
        }

        private object CreatedCallBack : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                //在新装app时会调用，调用时机为数据库build()之后，数据库升级时不调用此函数
                Log.d("DBManager", "onCreate: ")
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS \"CALL_LOG_UPLOAD_MANAGER\" (" + //

                            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + // 0: id

                            "\"CALL_LOG_ID\" TEXT," + // 1: call_log_id

                            "\"UUID\" TEXT," + // 2: uuid

                            "\"DATA\" TEXT," + // 3: data

                            "\"ADD_1\" TEXT," + // 4: ADD_1

                            "\"ADD_2\" TEXT," + // 5: ADD_2

                            "\"ADD_3\" TEXT);"
                ) // 6: ADD_3
            }
        }
    }
}