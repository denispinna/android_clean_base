package fr.denispinna.demo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.nhaarman.mockitokotlin2.whenever
import fr.denispinna.demo.injection.InjectionActivityTestRule
import fr.denispinna.demo.injection.test.DaggerTestComponent
import fr.denispinna.demo.api.LoginApi
import fr.denispinna.demo.api.model.LoginResponse
import fr.denispinna.demo.screen.login.LoginActivity
import fr.denispinna.demo.tool.fakeHttpException
import fr.denispinna.demo.tool.hasValueEqualTo
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest {
    private val email = "test@test.fr"
    private val password = "pass"
    private val token = "token"
    private val message = "message"
    private lateinit var successText: String
    private val errorCode = 401
    private val errorMessage = "Auth error"
    private val error = fakeHttpException(errorCode, errorMessage)

    @get:Rule
    val activityTestRule = InjectionActivityTestRule(
            LoginActivity::class.java,
            DaggerTestComponent.builder()
    )
    private lateinit var loginApi: LoginApi
    private lateinit var activity: LoginActivity


    /**
     * You should turn off the device animations & transitions for testing
     *              (Developer options > Window animation scale > Animation Off)
     *              (Developer options > Transition animation scale > Animation Off)
     *              (Developer options > Animation duration scale > Animation Off)
     */
    @Before
    fun init() {
        activity = activityTestRule.activity
        loginApi = activity.loginApi
        successText = activity.getString(R.string.success_screen_text)
    }

    @Test
    fun loginSuccess() {
        setupLoginSuccess()
        onView(withId(R.id.input_email)).perform(click(), typeText(email))
        onView(withId(R.id.input_password)).perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.bt_login)).perform(click())
        onView(withId(R.id.tv_success)).check(matches(hasValueEqualTo(successText)))
    }

    @Test
    fun loginError() {
        setupLoginError()
        onView(withId(R.id.input_email)).perform(click(), typeText(email))
        onView(withId(R.id.input_password)).perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.bt_login)).perform(click())
        onView(withId(R.id.tv_error)).check(matches(hasValueEqualTo(errorMessage)))
    }

    @Test
    fun loginRetry() {
        val retry = activity.getString(R.string.retry_button_text)

        setupLoginError()
        onView(withId(R.id.input_email)).perform(click(), typeText(email))
        onView(withId(R.id.input_password)).perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.bt_login)).perform(click())

        onView(withId(R.id.bt_login)).check(matches(hasValueEqualTo(retry)))

        setupLoginSuccess()
        onView(withId(R.id.bt_login)).perform(click())
        onView(withId(R.id.tv_success)).check(matches(hasValueEqualTo(successText)))
    }

    private fun setupLoginSuccess() {
        loginApi.apply {
            whenever(login(email = email, password = password))
                    .thenReturn(Single.just(LoginResponse(token = token, message = message)))
        }
    }

    private fun setupLoginError() {
        loginApi.apply {
            whenever(login(email = email, password = password))
                    .thenReturn(Single.error(error))
        }
    }
}
