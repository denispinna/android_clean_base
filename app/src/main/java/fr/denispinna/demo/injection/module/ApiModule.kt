package fr.denispinna.demo.injection.module

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import fr.denispinna.demo.BuildConfig.API_URL
import fr.denispinna.demo.api.LoginApi
import fr.denispinna.demo.api.LoginApiImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    @Provides
    internal fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder()
                .setLenient()
                .create()
        return Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    internal fun provideLoginApi(retrofit: Retrofit): LoginApi = LoginApiImpl(retrofit = retrofit)
}
