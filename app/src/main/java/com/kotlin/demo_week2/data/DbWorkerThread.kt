package com.kotlin.demo_week2.data

import android.os.Handler
import android.os.HandlerThread

class DbWorkerThread(threadName: String) : HandlerThread(threadName) {
    private lateinit var mWorkerThread: Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerThread = Handler(looper)
    }

    fun postTask(task: Runnable) {
        mWorkerThread.post(task)
    }
}