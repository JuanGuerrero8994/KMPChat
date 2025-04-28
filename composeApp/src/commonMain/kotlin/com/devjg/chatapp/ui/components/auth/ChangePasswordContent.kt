package com.devjg.chatapp.ui.components.auth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun ChangePasswordContent(
    email: String,
    currentPassword: String,
    newPassword: String,
    confirmNewPassword: String,
    errorMessage: String?,
    isSubmitting: Boolean,
    onEmailChange: (String) -> Unit,
    onCurrentPasswordChange: (String) -> Unit,
    onNewPasswordChange: (String) -> Unit,
    onConfirmNewPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Cambiar Contraseña", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = currentPassword,
            onValueChange = onCurrentPasswordChange,
            label = { Text("Contraseña actual") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = newPassword,
            onValueChange = onNewPasswordChange,
            label = { Text("Nueva contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = confirmNewPassword,
            onValueChange = onConfirmNewPasswordChange,
            label = { Text("Confirmar nueva contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isSubmitting
        ) {
            Text("Cambiar contraseña")
        }

        if (!errorMessage.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}