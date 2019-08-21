package fr.denispinna.demo.injection

import android.app.Activity
import androidx.test.rule.ActivityTestRule
import dagger.android.AndroidInjector
import androidx.test.core.app.ApplicationProvider
import fr.denispinna.demo.ApplicationImpl
import fr.denispinna.demo.injection.test.TestComponent

class InjectionActivityTestRule<T : Activity>(
        activityClass: Class<T>,
        private val builder: TestComponent.Builder
) : ActivityTestRule<T>(activityClass) {

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()
        // setup test component before activity launches
        val app = ApplicationProvider.getApplicationContext<ApplicationImpl>()
        val testComponent = builder.applicationBind(app).build()
        app.setApplicationInjector(testComponent)
    }
}

private fun ApplicationImpl.setApplicationInjector(injector: AndroidInjector<ApplicationImpl>) {
    this.injector = injector.also {
        it.inject(this)
    }
}
