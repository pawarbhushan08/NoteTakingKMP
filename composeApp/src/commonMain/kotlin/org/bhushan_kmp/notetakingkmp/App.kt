package org.bhushan_kmp.notetakingkmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.bhushan_kmp.notetakingkmp.di.commonModule
import org.bhushan_kmp.notetakingkmp.ui.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin

@Composable
@Preview
fun App() {
    startKoin {
        modules(commonModule)
    }
    MaterialTheme {
        Navigator(HomeScreen())
    }
}