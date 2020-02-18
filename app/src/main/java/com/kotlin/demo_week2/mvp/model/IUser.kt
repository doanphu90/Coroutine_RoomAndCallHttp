package com.kotlin.demo_week2.mvp.model

interface IUser {
    fun isInvalidLogin(email: String, pass: String): Int
    fun isInvalidRegister(
        user: String,
        pass: String,
        email: String,
        addr: String,
        gender: String
    ): Int
}