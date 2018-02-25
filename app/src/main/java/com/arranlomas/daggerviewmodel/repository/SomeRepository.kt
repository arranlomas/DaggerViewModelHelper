package com.arranlomas.daggerviewmodel.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

/**
 * Created by arran on 25/02/2018.
 */
class SomeRepository: ISomeRepository {
    override fun getSomeStuff(): LiveData<List<Int>> {
        val liveData = MutableLiveData<List<Int>>()
        liveData.value = listOf(1, 2, 3)
        return liveData
    }
}