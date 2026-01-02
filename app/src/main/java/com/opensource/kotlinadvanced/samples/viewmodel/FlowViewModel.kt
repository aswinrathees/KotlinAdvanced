package com.opensource.kotlinadvanced.samples.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FlowViewModel: ViewModel() {

    val viewModelFlow = flow<Int> {
        for (i in 1..10) {
            emit(i)
            delay(1000)
        }
    }
}