package fr.denispinna.demo.api

import android.app.Activity
import android.util.Log
import fr.denispinna.demo.util.closeKeyboard
import io.reactivex.android.schedulers.AndroidSchedulers
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import fr.denispinna.demo.R
import fr.denispinna.demo.screen.login.LoginViewModel
import io.reactivex.disposables.CompositeDisposable

private const val TAG = "LoginController"

internal class LoginController(
        private val disposable: CompositeDisposable,
        private val viewModel: LoginViewModel,
        private val loginApi: LoginApi) {

    fun postLogin(activity: Activity, email: String, password: String) {
        closeKeyboard(activity)
        viewModel.loading.value = true
        disposable.add(
                loginApi.login(email, password)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    viewModel.loginSuccessResponse.value = result
                                    Log.d(TAG, result.toString())
                                },
                                { error ->
                                    if (error is HttpException) {
                                        viewModel.loading.value = false
                                        val apiError = error.toErrorResponse()
                                        val errorMessage = apiError?.message ?: activity.getString(R.string.login_generic_error_message)
                                        viewModel.errorMessage.value = errorMessage
                                    }
                                    viewModel.error.value = error
                                    Log.e(TAG, error.toString())
                                })
        )
    }

    fun cancelLogin() {
        disposable.clear()
    }

}
