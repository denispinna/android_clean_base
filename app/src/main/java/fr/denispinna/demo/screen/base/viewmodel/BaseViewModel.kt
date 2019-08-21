package fr.denispinna.demo.screen.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    val toastMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}
