package com.devjg.chatapp.ui.components.base

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.devjg.chatapp.core.Resource
import com.devjg.chatapp.ui.components.dialog.LoadingDialog

@Composable
fun <T> BaseResourceComponent(
    resource: Resource<T>,
    isLoadingDialog: Boolean = true,
    onSuccess: @Composable (T) -> Unit,
    onError: @Composable (String) -> Unit = {
        Text(
            text = it,
            color = MaterialTheme.colors.error
        )
    },
    onLoading: @Composable () -> Unit = {
        if (isLoadingDialog) LoadingDialog(true)
    }
) {
    when (resource) {
        is Resource.Loading -> onLoading()
        is Resource.Error -> onError(resource.exception.message ?: "Error desconocido")
        is Resource.Success -> onSuccess(resource.data)
    }
}