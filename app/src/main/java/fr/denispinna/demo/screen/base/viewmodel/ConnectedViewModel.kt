package fr.denispinna.demo.screen.base.viewmodel

import androidx.lifecycle.MutableLiveData
import fr.denispinna.demo.component.SingleLiveEvent

open class ConnectedViewModel: BaseViewModel() {
    val loading by lazy {
        MutableLiveData<Boolean>()
    }

    val error by lazy {
        SingleLiveEvent<Throwable?>()
    }

    val errorMessage by lazy {
        MutableLiveData<String?>()
    }
}
