package fr.denispinna.demo.screen.login

import androidx.lifecycle.MutableLiveData
import fr.denispinna.demo.api.LoginApi
import fr.denispinna.demo.api.model.LoginResponse
import fr.denispinna.demo.screen.base.viewmodel.ConnectedViewModel

class LoginViewModel: ConnectedViewModel() {
    val loginSuccssResponse: MutableLiveData<LoginResponse> by lazy {
        MutableLiveData<LoginResponse>()
    }
}
