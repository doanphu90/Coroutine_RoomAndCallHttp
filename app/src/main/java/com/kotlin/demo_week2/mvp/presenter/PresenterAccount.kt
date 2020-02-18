package com.kotlin.demo_week2.mvp.presenter

import com.kotlin.demo_week2.mvp.view.IFunSource
import com.kotlin.demo_week2.mvp.view.IMainView
import com.kotlin.demo_week2.unit.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PresenterAccount(
    val IOContext: CoroutineContext,
    val mainContext: CoroutineContext, val sourData: IFunSource, val iView: IMainView
) : IAccountPresenter, CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    override fun fetchAccount() {
        CoroutineScope(mainContext).launch {
            val result = sourData.callHttp(Contact.base_url, IOContext)
            with(result.await()) {
                iView.showView(this)
            }

        }
    }

}