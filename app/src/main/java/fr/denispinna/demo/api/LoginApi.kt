package fr.denispinna.demo.api

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import com.google.gson.annotations.SerializedName
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import fr.denispinna.demo.api.model.LoginRequest
import fr.denispinna.demo.api.model.LoginResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    fun login(email: String, password: String): Single<LoginResponse>
}

class LoginApiImpl(retrofit: Retrofit) : LoginApi {
    private val retrofitService: RetrofitApi = retrofit.create(RetrofitApi::class.java)

    override fun login(email: String, password: String): Single<LoginResponse> {
        val request = LoginRequest(email = email, password = password)
        return retrofitService.login(request).subscribeOn(Schedulers.io())
    }

    private interface RetrofitApi {
        @POST("test/authenticate")
        fun login(@Body request: LoginRequest): Single<LoginResponse>
    }
}

internal data class ApiErrorResponse(
        @Json(name = "message")
        val message: String = "")

internal fun HttpException.toErrorResponse(): ApiErrorResponse? {
    val errorBody = this.response().errorBody()?.charStream()?.readText ()
    return if(errorBody != null)
        Klaxon().parse(errorBody)!!
    else
        null
}
