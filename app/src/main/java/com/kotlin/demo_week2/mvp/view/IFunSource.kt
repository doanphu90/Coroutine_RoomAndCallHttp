package com.kotlin.demo_week2.mvp.view

import com.kotlin.demo_week2.mvp.model.Messages
import kotlinx.coroutines.Deferred
import kotlin.coroutines.CoroutineContext

interface IFunSource {
    fun callHttp(url:String, mCoroutineContext: CoroutineContext):Deferred<List<Messages>?>
}