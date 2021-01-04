package com.devlab74.projectone.ui

import com.devlab74.projectone.util.DataState

interface DataStateListener {
    fun onDataStateChange(dataState: DataState<*>?)
}