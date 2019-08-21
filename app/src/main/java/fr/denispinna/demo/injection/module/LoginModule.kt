package fr.denispinna.demo.injection.module

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import fr.denispinna.demo.injection.annotation.ActivityScope
import fr.denispinna.demo.screen.login.LoginActivity
import fr.denispinna.demo.screen.login.LoginViewModel


@Module
class LoginModule {
    @Provides
    @ActivityScope
    fun provideViewModel(activity: LoginActivity): LoginViewModel {
        return ViewModelProviders.of(activity).get(LoginViewModel::class.java)
    }
}
