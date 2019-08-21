package fr.denispinna.demo.injection.module

import android.app.Activity
import dagger.Module
import fr.denispinna.demo.injection.annotation.ActivityScope
import dagger.Provides


@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    internal fun activity(): Activity {
        return this.activity
    }
}
