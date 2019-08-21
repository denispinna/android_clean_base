package fr.denispinna.demo.screen.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ConnectedViewModel: BaseViewModel() {
    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val error: MutableLiveData<Throwable?> by lazy {
        MutableLiveData<Throwable?>()
    }

    val errorMessage: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }
}
