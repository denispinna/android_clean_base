package fr.denispinna.demo.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import fr.denispinna.demo.injection.annotation.ActivityScope
import fr.denispinna.demo.screen.home.HomeActivity
import fr.denispinna.demo.screen.login.LoginActivity

@Module
abstract class InjectorModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun contributeLoginActivityInjector(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun contributeSuccessActivityInjector(): HomeActivity
}
