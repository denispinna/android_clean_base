package fr.denispinna.demo

import fr.denispinna.demo.api.LoginApiImpl
import fr.denispinna.demo.api.toErrorResponse
import fr.denispinna.demo.tool.buildRetrofit
import fr.denispinna.demo.tool.enqueueResponse
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class LoginApiTest {
    private lateinit var mockWebServerRule: MockWebServer
    private lateinit var loginApiImpl: LoginApiImpl

    @Before
    fun setUp() {
        mockWebServerRule = MockWebServer()
        mockWebServerRule.start()
        loginApiImpl = LoginApiImpl(mockWebServerRule.buildRetrofit())
    }

    @Test
    fun loginSuccess() {
        mockWebServerRule.enqueueResponse("login_success.json", SUCCESS_CODE)

        val login = loginApiImpl.login("", "")
        val testObserver = login.test()
        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        val response = testObserver.values()[0]
        assertThat(response.message).isEqualTo(SUCCESS_MESSAGE)
        assertThat(response.token).isEqualTo(SUCCESS_TOKEN)
    }

    @Test
    fun loginError() {
        mockWebServerRule.enqueueResponse("login_error.json", ERROR_CODE)

        val login = loginApiImpl.login("", "")
        val testObserver = login.test()
        testObserver.awaitTerminalEvent()
        testObserver.assertError {error ->
            val message = (error as HttpException).toErrorResponse()?.message
            message.equals(ERROR_MESSAGE)
        }
    }
}

private const val SUCCESS_CODE = 200
private const val SUCCESS_MESSAGE = "success"
private const val SUCCESS_TOKEN = "token"
private const val ERROR_CODE = 401
private const val ERROR_MESSAGE = "error"
