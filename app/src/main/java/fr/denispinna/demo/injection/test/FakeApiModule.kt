package fr.denispinna.demo.injection.test

import dagger.Module
import dagger.Provides
import fr.denispinna.demo.api.LoginApi
import org.mockito.Mockito.mock

@Module
class FakeApiModule {
    @Provides
    internal fun provideLoginApi(): LoginApi = mock(LoginApi::class.java)
}
