package com.kotlin.demo_week2.mvp.view

import com.kotlin.demo_week2.mvp.model.Messages

interface IMainView {
    fun showView(infoData: List<Messages>?)
}