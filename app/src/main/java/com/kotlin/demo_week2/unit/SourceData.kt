package com.kotlin.demo_week2.unit

import android.util.Log
import com.kotlin.demo_week2.mvp.model.InfoData
import com.kotlin.demo_week2.mvp.model.Messages
import com.kotlin.demo_week2.mvp.view.IFunSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.CoroutineContext

object SourceData : IFunSource {
    override fun callHttp(url: String, mCoroutineContext: CoroutineContext): Deferred<List<Messages>?> {
        return CoroutineScope(mCoroutineContext).async {
            var inputStream: InputStream? = null
            var result: List<Messages> = listOf()
            val url: URL = URL(url)
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            try {
                conn.connect()
                inputStream = conn.inputStream
            } catch (e: Exception) {
                Log.d("DoanPhu", "Err: ${e.message}")
            }
            if (inputStream != null) {
                val bufferedReader: BufferedReader? =
                    BufferedReader(InputStreamReader(inputStream))
                var line: String? = bufferedReader?.readLine()
                var resultJson: String = ""
                while (line != null) {
                    resultJson += line
                    line = bufferedReader?.readLine()
                }
                result = convertInputStreamToString(resultJson)
            }
            result
        }
    }

    private fun convertInputStreamToString(resultJson: String): List<Messages> {
        var listInfo: MutableList<Messages> = arrayListOf()
        if (resultJson.isNotEmpty()) {
            var jsonObject = JSONObject(resultJson)
            try {
                var jsonArray = jsonObject.getJSONArray("messages")
                for (jsonIndex in 0..(jsonArray.length() - 1)) {
                    Log.d("JSON", jsonArray.getJSONObject(jsonIndex).getString("from"))
                    var id: String = jsonArray.getJSONObject(jsonIndex).getString("id")
                    var from: String = jsonArray.getJSONObject(jsonIndex).getString("from")
                    var email: String = jsonArray.getJSONObject(jsonIndex).getString("email")
                    var subject: String = jsonArray.getJSONObject(jsonIndex).getString("subject")
                    var message: String = jsonArray.getJSONObject(jsonIndex).getString("message")
                    var date: String = jsonArray.getJSONObject(jsonIndex).getString("date")
                    listInfo.add(Messages(id, from, email, subject, message, date))
                }
            } catch (e: Exception) {
                e.message
            }
        }
        return listInfo
    }
}