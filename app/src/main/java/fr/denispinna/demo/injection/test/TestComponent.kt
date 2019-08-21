package fr.denispinna.demo.injection.test

import dagger.BindsInstance
import fr.denispinna.demo.ApplicationImpl
import fr.denispinna.demo.injection.module.InjectorModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    InjectorModule::class,
    FakeApiModule::class
])

interface TestComponent : AndroidInjector<ApplicationImpl> {
    @Component.Builder
    interface Builder {

        fun build(): TestComponent

        @BindsInstance
        fun applicationBind(application: ApplicationImpl): Builder

    }
}
