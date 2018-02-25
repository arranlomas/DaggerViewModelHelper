package com.arranlomas.daggerviewmodel.di

import com.arranlomas.daggerviewmodel.MainApplication
import com.arranlomas.daggerviewmodel.repository.DependencyProviderModule
import com.arranlomas.daggerviewmodel.ui.MainActivityModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by arran on 25/02/2018.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        MainActivityModule::class,
        DependencyProviderModule::class,
        ViewModelModule::class))
interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(app: MainApplication)
}

