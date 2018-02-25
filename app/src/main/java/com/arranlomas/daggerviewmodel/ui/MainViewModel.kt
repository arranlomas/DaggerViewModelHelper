package com.arranlomas.daggerviewmodel.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.arranlomas.daggerviewmodel.repository.ISomeRepository
import javax.inject.Inject

/**
 * Created by arran on 25/02/2018.
 */
class MainViewModel @Inject constructor(private val someRepo: ISomeRepository) : ViewModel() {
    fun getSomeStuff(): LiveData<List<Int>> {
        return someRepo.getSomeStuff()
    }
}