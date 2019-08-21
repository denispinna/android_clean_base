package fr.denispinna.demo.tool

import fr.denispinna.demo.tool.TestUtils.Companion.loadResponseBody
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset

class TestUtils {
    companion object {
        fun loadResponseBody(name: String): String {
            val source = javaClass.classLoader.getResourceAsStream(name).source().buffer()
            return source.readString(Charset.forName("UTF-8"))
        }
    }
}

fun MockWebServer.enqueueResponse(resourceName: String, responseCode: Int) {
    enqueue(
            MockResponse()
                    .setBody(loadResponseBody(resourceName))
                    .setHeader("Content-Type", "application/json;charset=UTF-8")
                    .setResponseCode(responseCode)
    )
}

fun MockWebServer.buildRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(url("/"))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}
