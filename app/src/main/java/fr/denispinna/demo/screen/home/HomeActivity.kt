package fr.denispinna.demo.screen.home

import android.os.Bundle
import fr.denispinna.demo.R
import fr.denispinna.demo.screen.base.BaseActivity
import fr.denispinna.demo.screen.base.viewmodel.BaseViewModel
import javax.inject.Inject

class HomeActivity : BaseActivity<BaseViewModel>() {
    @Inject
    override lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
    }

    override fun onStart() {
        super.onStart()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
