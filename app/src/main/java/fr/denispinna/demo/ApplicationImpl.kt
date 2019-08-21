package fr.denispinna.demo

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import fr.denispinna.demo.injection.component.DaggerMyApplicationComponent

class ApplicationImpl : DaggerApplication() {

    lateinit var injector: AndroidInjector<out DaggerApplication>

    override fun onCreate() {
        injector = DaggerMyApplicationComponent.builder().applicationBind(this).build()

        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return injector
    }
}
