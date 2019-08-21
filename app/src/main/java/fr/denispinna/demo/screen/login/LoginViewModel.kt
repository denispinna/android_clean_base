package fr.denispinna.demo.screen.login

import fr.denispinna.demo.api.model.LoginResponse
import fr.denispinna.demo.component.SingleLiveEvent
import fr.denispinna.demo.screen.base.viewmodel.ConnectedViewModel

class LoginViewModel: ConnectedViewModel() {
    val loginSuccessResponse by lazy {
        SingleLiveEvent<LoginResponse>()
    }
}
