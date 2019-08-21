package fr.denispinna.demo.screen.base

import androidx.lifecycle.Observer
import fr.denispinna.demo.screen.base.viewmodel.BaseViewModel
import fr.denispinna.demo.screen.base.viewmodel.ConnectedViewModel

abstract class BaseConnectedActivity<MV : ConnectedViewModel> : BaseActivity<MV>() {

    abstract fun showLoading(show: Boolean)

    abstract fun showError(throwable: Throwable?)

    abstract fun showErrorMessage(message: String?)

    override fun initViewModel() {
        super.initViewModel()
        viewModel.error.observe(this, Observer { value ->
            showError(value)
        })
        viewModel.errorMessage.observe(this, Observer { value ->
            showErrorMessage(value)
        })
        viewModel.loading.observe(this, Observer { value ->
            showLoading(value)
        })
    }
}
