package com.kotlin.demo_week2.data.dao

import androidx.room.*
import com.kotlin.demo_week2.data.entity.User

@Dao
interface UserDataDao {
    @Query("SELECT * FROM userInformation")
    fun getAllData(): List<User>

    @Query("SELECT * FROM userInformation WHERE email == :emaill")
    fun getDataUserByID(emaill: String): Boolean

    @Query("SELECT * FROM userInformation WHERE email == :emaill AND pass ==:password")
    fun checkExistsUser(emaill: String, password: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(newUser: User)

    @Update
    fun updateAccount(newUser: User)

    @Delete
    fun deleteAccount(user: User)
}