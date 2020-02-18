package com.kotlin.demo_week2.mvp.model

import android.text.TextUtils
import android.util.Patterns
import com.kotlin.demo_week2.data.entity.User

class IsUser : IUser {
    override fun isInvalidLogin(email: String, pass: String): Int {
        when {
            TextUtils.isEmpty(email) -> return 1
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> return 2
            pass.length < 6 -> return 3
            else -> return 0
        }
    }

    override fun isInvalidRegister(
        user: String, pass: String, email: String, addr: String, gender: String
    ): Int {
        when {
            TextUtils.isEmpty(user) -> return 1
            TextUtils.isEmpty(email) -> return 2
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> return 3
            pass.length < 6 -> return 4
            TextUtils.isEmpty(addr) -> return 5
            TextUtils.isEmpty(gender) -> return 6
            else -> return 0
        }
    }
}