package com.opensource.kotlinadvanced.samples

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.opensource.kotlinadvanced.ui.theme.KotlinAdvancedTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FlowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // In real projects flow should be inside a ViewModel
        val activityFlow = flow {
            for (i in 1..10) {
                emit(i)
                delay(1000L)
            }
        }

        // Sample example with a CoroutineScope for collecting the data
        CoroutineScope(Dispatchers.Main).launch {
            activityFlow.collect {
                Log.i("FA", "Current index: $it")
            }
        }

        setContent {
            KotlinAdvancedTheme {
                // Collect the flow as a state
                val currentIndex by activityFlow.collectAsStateWithLifecycle(initialValue = 0)

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Current Index: $currentIndex",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}