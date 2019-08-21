package fr.denispinna.demo.screen.base.viewmodel

import androidx.lifecycle.ViewModel
import fr.denispinna.demo.component.SingleLiveEvent

open class BaseViewModel: ViewModel() {
    val toastMessage by lazy {
        SingleLiveEvent<String>()
    }
}
