package com.arranlomas.daggerviewmodel.repository

import dagger.Module
import dagger.Provides

/**
 * Created by arran on 25/02/2018.
 */
@Module
class DependencyProviderModule {
    @Provides
    fun providesScoreRepository(): ISomeDependency {
        return SomeDependency()
    }
}