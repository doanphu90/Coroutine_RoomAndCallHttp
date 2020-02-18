package com.kotlin.demo_week2.mvp.view

import android.view.View

interface ILoginView {
    fun onLoginFail(message: Int)
    fun onLoginSuccess()
}