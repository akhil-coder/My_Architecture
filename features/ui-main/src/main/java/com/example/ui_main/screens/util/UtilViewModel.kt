package com.example.ui_main.screens.util

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.core.domain.UIComponent
import com.example.core.util.Logger
import com.example.core.util.exhaustive
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class UtilViewModel @Inject constructor(
    @Named("mainLogger") private val logger: Logger,
) : ViewModel() {

    private val _utilState = mutableStateOf(UtilState())
    val utilState: State<UtilState> = _utilState

    fun onEventChange(events: UtilEvents) {
        when (events) {
            is UtilEvents.AddPermission -> {
                if (!events.isGranted) {
                    addToPermissionQueue(events.permission)
                } else {
                    //
                }
            }
            UtilEvents.OnRemoveHeadFromQueue -> {
                try {
                    val queue = utilState.value.errorQueue
                    queue.remove()
                    _utilState.value = utilState.value.copy(
                        errorQueue = queue
                    )
                } catch (e: Exception) {
                    logger.log("Nothing to remove from queue")
                }
            }
            UtilEvents.OnRemovePermission -> {
                try {
                    val queue = utilState.value.permissionQueue
                    queue.remove()
                    _utilState.value = utilState.value.copy(
                        permissionQueue = queue
                    )
                } catch (e: Exception) {
                    logger.log("Nothing to remove from queue")
                }
            }
        }.exhaustive
    }

    private fun addToMessageQueue(uiComponent: UIComponent) {
        val queue = utilState.value.errorQueue
        queue.add(uiComponent)
        _utilState.value = utilState.value.copy(
            errorQueue = queue
        )
    }

    private fun addToPermissionQueue(permission: String) {
        val queue = utilState.value.permissionQueue
        queue.add(permission)
        _utilState.value = utilState.value.copy(
            permissionQueue = queue
        )
    }

}

sealed class UtilEvents {
    data class AddPermission(val permission: String, val isGranted: Boolean) : UtilEvents()
    object OnRemovePermission : UtilEvents()
    object OnRemoveHeadFromQueue : UtilEvents()
}