package com.arranlomas.daggerviewmodel.repository

import android.arch.lifecycle.LiveData

/**
 * Created by arran on 25/02/2018.
 */
interface ISomeDependency {
    fun getSomeStuff(): LiveData<List<Int>>
}
