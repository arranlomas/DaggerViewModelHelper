package com.arranlomas.daggerviewmodel.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by arran on 25/02/2018.
 */
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity
}