package fr.denispinna.demo.tool

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import okhttp3.ResponseBody.Companion.toResponseBody
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import retrofit2.Response


internal fun hasValueEqualTo(content: String): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("Has EditText/TextView the value:  $content")
        }

        override fun matchesSafely(view: View?): Boolean {
            if (view !is TextView && view !is EditText) {
                return false
            }
            val text: String = when (view) {
                is TextView -> view.text.toString()
                is EditText -> view.text.toString()
                is Button -> view.text.toString()
                else -> ""
            }

            return text.equals(content, ignoreCase = true)
        }
    }
}


private const val ERROR_BODY_TEMPLATE = "{\"message\":\"%s\"}"
internal fun fakeHttpException(code: Int, message: String): HttpException {
    val errorBody = String.format(ERROR_BODY_TEMPLATE, message).toResponseBody(null)
    return HttpException(Response.error<Any>(code, errorBody))
}
