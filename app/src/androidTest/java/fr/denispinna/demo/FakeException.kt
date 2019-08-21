package fr.denispinna.demo

import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class FakeException(
        private val exceptionCode: Int,
        errorBody: String):
        HttpException(Response.error<String>(exceptionCode, ResponseBody.create(null, errorBody))) {
}
