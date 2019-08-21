package fr.denispinna.demo.injection.component

import dagger.BindsInstance
import fr.denispinna.demo.ApplicationImpl
import fr.denispinna.demo.injection.module.ApiModule
import fr.denispinna.demo.injection.module.InjectorModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    InjectorModule::class,
    ApiModule::class
])
interface MyApplicationComponent : AndroidInjector<ApplicationImpl> {
    @Component.Builder
    interface Builder {

        fun build(): MyApplicationComponent

        @BindsInstance
        fun applicationBind(application: ApplicationImpl): Builder

    }
}
