package com.kotlin.demo_week2.mvp.model

data class InfoData(var messages: List<Messages>)

data class Messages(
    val id: String,
    val from: String,
    val email: String,
    val subject: String,
    val message: String,
    val date: String
)