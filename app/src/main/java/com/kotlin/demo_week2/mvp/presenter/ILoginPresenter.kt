package com.kotlin.demo_week2.mvp.presenter

import android.view.View
import com.kotlin.demo_week2.data.entity.User

interface ILoginPresenter {
    fun isCheckValidLogin(email: String, passWord: String):Boolean

    fun isCheckValidRegister(user: User):Boolean

    fun loginApp(email:String, pass:String)

    fun registerAccount(user: User)
}