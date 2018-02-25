package com.arranlomas.daggerviewmodel

import com.arranlomas.daggerviewmodel.di.AppComponent
import com.arranlomas.daggerviewmodel.di.DaggerAppComponent
import com.arranlomas.daggerviewmodelhelper.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by arran on 25/02/2018.
 */
class MainApplication : DaggerApplication() {

    lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        AppInjector.init(this)
        appComponent = DaggerAppComponent.create()
        appComponent.inject(this)
        return appComponent
    }
}