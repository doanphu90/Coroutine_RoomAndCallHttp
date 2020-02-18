package com.kotlin.demo_week2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userInformation")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "userName") var userName: String,
    @ColumnInfo(name = "pass") var pass: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "gender") var gender: String
) {
    constructor() : this(null, "", "", "", "", "")
}
