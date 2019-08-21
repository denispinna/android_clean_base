package fr.denispinna.demo.screen.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import fr.denispinna.demo.api.LoginApi
import fr.denispinna.demo.api.LoginController
import fr.denispinna.demo.screen.base.BaseConnectedActivity
import fr.denispinna.demo.screen.home.HomeActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import fr.denispinna.demo.R
import fr.denispinna.demo.screen.base.viewmodel.BaseViewModel
import fr.denispinna.demo.screen.base.viewmodel.ConnectedViewModel
import javax.inject.Inject

class LoginActivity : BaseConnectedActivity<LoginViewModel>() {
    private var email = ""
    private var password = ""
    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false
    private var isLoading = false
    private lateinit var loginController: LoginController

    @BindView(R.id.bt_login)
    lateinit var btLogin: Button
    @BindView(R.id.input_email)
    lateinit var inputEmail: TextInputEditText
    @BindView(R.id.input_password)
    lateinit var inputPassword: TextInputEditText
    @BindView(R.id.email_layout)
    lateinit var emailLayout: TextInputLayout
    @BindView(R.id.password_layout)
    lateinit var passwordLayout: TextInputLayout
    @BindView(R.id.tv_error)
    lateinit var tvError: TextView

    @Inject
    lateinit var loginApi: LoginApi

    @Inject
    override lateinit var viewModel: LoginViewModel

    private val emailInputTextChanged = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val emailText = inputEmail.text
            if (emailText != null) {
                email = emailText.toString()
            }
            isEmailValid = email.contains("@")
            if (!isEmailValid) {
                emailLayout.error = getString(R.string.email_invalid_message)
            } else {
                emailLayout.error = null
            }
            checkValidLogin()
        }

        override fun afterTextChanged(s: Editable) {}
    }

    private val passwordInputTextChanged = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val passwordText = inputPassword.text
            if (passwordText != null) {
                password = passwordText.toString()
            }
            isPasswordValid = password.isNotEmpty()
            if (!isPasswordValid) {
                passwordLayout.error = getString(R.string.password_invalid_message)
            } else {
                passwordLayout.error = null
            }
            checkValidLogin()
        }

        override fun afterTextChanged(s: Editable) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
    }

    override fun onStart() {
        super.onStart()
        inputEmail.addTextChangedListener(emailInputTextChanged)
        inputPassword.addTextChangedListener(passwordInputTextChanged)
        loginController = LoginController(
                disposable = disposable,
                viewModel = viewModel as LoginViewModel,
                loginApi = loginApi)
    }

    override fun showLoading(isLoading: Boolean) {
        this.isLoading = isLoading
        if (isLoading) {
            btLogin.setText(R.string.cancel_button_text)
        }
    }

    override fun showError(throwable: Throwable) {
        btLogin.setText(R.string.retry_button_text)
        showLoading(false)

    }

    override fun showErrorMessage(message: String?) {
        if(message!= null) {
            tvError.visibility = VISIBLE
        } else {
            tvError.visibility = GONE
        }
        tvError.text = message
    }
    override fun initViewModel() {
        super.initViewModel()
        val loginViewModel = viewModel as LoginViewModel
        loginViewModel.loginSuccssResponse.observe(this, Observer { value ->
            val successMessage = String.format("Token : %s \n %s", value.token, value.message)

            loginViewModel.toastMessage.value = successMessage
            loginViewModel.error.value = null
            loginViewModel.errorMessage.value = null
            loginViewModel.loading.value = false

            loginSuccess()
        })
    }

    private fun loginSuccess() {
        btLogin.setText(R.string.login_button_text)
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun checkValidLogin() {
        btLogin.isEnabled = isEmailValid && isPasswordValid
    }

    @OnClick(R.id.bt_login)
    internal fun login() {
        val loginViewModel = viewModel as LoginViewModel
        loginViewModel.errorMessage.value = null

        if (isLoading) {
            loginController.cancelLogin()
        } else {
            loginController.postLogin(activity = this, email = email, password = password)
        }

    }
}
