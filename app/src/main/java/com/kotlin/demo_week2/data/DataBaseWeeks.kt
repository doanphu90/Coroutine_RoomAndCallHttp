package com.kotlin.demo_week2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kotlin.demo_week2.data.dao.UserDataDao
import com.kotlin.demo_week2.data.entity.User

@Database(entities = [User::class], version = 1)
abstract class DataBaseWeeks:RoomDatabase() {
    companion object {
        private var INSTANCE: DataBaseWeeks? = null

        fun getAppDataBase(context: Context): DataBaseWeeks? {
            if (INSTANCE == null){
                synchronized(DataBaseWeeks::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataBaseWeeks::class.java, "weekDB.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

    abstract fun userDao():UserDataDao
}