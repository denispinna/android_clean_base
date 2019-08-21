package fr.denispinna.demo.injection.annotation

import javax.inject.Scope

/**
 * Used to inject object per activity
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope
