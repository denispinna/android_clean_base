package fr.denispinna.demo.screen.base

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import dagger.android.support.DaggerAppCompatActivity
import fr.denispinna.demo.screen.base.viewmodel.BaseViewModel
import fr.denispinna.demo.screen.base.viewmodel.ConnectedViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<MV: BaseViewModel> : DaggerAppCompatActivity() {
    abstract var viewModel: MV

    val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onStart() {
        super.onStart()
        initViewModel()
        ButterKnife.bind(this)
    }

    open fun initViewModel() {
        viewModel.toastMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }
}
