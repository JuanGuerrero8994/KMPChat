package com.devjg.chatapp.ui.screen.auth

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devjg.chatapp.domain.model.User
import com.devjg.chatapp.ui.components.base.BaseResourceComponent
import com.devjg.chatapp.ui.components.scaffold.BottomNavScreen
import com.devjg.chatapp.ui.navigation.Destinations

@Composable
fun ChangePasswordScreen(authViewModel: AuthViewModel, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmNewPassword by remember { mutableStateOf("") }
    var isLoginClicked by remember { mutableStateOf(false) }

    var errorMessages by remember { mutableStateOf<List<String>>(emptyList()) }

    val authState = authViewModel.state.collectAsState()

    LaunchedEffect(isLoginClicked) {
        if (isLoginClicked && errorMessages.isEmpty()) {
            val user = User(email = email, password = currentPassword)
            authViewModel.changePassword(user, newPassword)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Cambiar contraseña", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(32.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Contraseña actual
        OutlinedTextField(
            value = currentPassword,
            onValueChange = { currentPassword = it },
            label = { Text("Contraseña actual") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nueva contraseña
        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("Nueva contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirmación de contraseña
        OutlinedTextField(
            value = confirmNewPassword,
            onValueChange = { confirmNewPassword = it },
            label = { Text("Confirmar nueva contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar errores
        errorMessages.forEach { message ->
            Text(
                text = message,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val errors = mutableListOf<String>()

                if (email.isBlank()) errors.add("El correo electrónico no puede estar vacío.")
                if (currentPassword.isBlank()) errors.add("La contraseña actual no puede estar vacía.")
                if (newPassword.isBlank()) errors.add("La nueva contraseña no puede estar vacía.")
                if (confirmNewPassword.isBlank()) errors.add("Debes confirmar la nueva contraseña.")
                if (newPassword != confirmNewPassword) errors.add("Las contraseñas no coinciden.")
                if (newPassword == currentPassword && newPassword.isNotEmpty()) errors.add("La nueva contraseña no puede ser igual a la anterior.")

                errorMessages = errors

                if (errors.isEmpty()) {
                    isLoginClicked = true
                    val user = User(email = email, password = currentPassword)
                    authViewModel.changePassword(user, newPassword)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoginClicked
        ) {
            Text("Cambiar contraseña")
        }

        Spacer(modifier = Modifier.height(16.dp))

        BaseResourceComponent(
            resource = authState.value,
            isLoadingDialog = isLoginClicked,
            onSuccess = {
                isLoginClicked = false
                navController.navigate(BottomNavScreen.Home.route) {
                    popUpTo(Destinations.AuthScreen.route) { inclusive = true }
                }
            },
            onError = { message ->
                isLoginClicked = false
                errorMessages = listOf(message)
            }
        )
    }
}