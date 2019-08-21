package fr.denispinna.demo.injection.module

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import fr.denispinna.demo.injection.annotation.ActivityScope
import fr.denispinna.demo.screen.base.viewmodel.BaseViewModel
import fr.denispinna.demo.screen.home.HomeActivity


@Module
class HomeModule {
    @Provides
    @ActivityScope
    fun provideViewModel(activity: HomeActivity): BaseViewModel {
        return ViewModelProviders.of(activity).get(BaseViewModel::class.java)
    }
}
