package com.example.nexcontacts.ui.common

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarDuration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object SnackbarController {

    private var hostState: SnackbarHostState? = null
    private var scope: CoroutineScope? = null

    fun init(state: SnackbarHostState, scope: CoroutineScope) {
        hostState = state
        this.scope = scope
    }

    fun show(message: String) {
        scope?.launch {
            hostState?.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }
}
