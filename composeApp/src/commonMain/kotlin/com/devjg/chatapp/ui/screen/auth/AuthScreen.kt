package com.devjg.chatapp.ui.screen.auth

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devjg.chatapp.domain.model.User
import com.devjg.chatapp.ui.components.base.BaseResourceComponent
import com.devjg.chatapp.ui.components.scaffold.BottomNavScreen
import com.devjg.chatapp.ui.navigation.Destinations

@Composable
fun AuthScreen(
    authViewModel: AuthViewModel,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoginClicked by remember { mutableStateOf(false) }
    val authState = authViewModel.state.collectAsState()

    // La autenticación solo se debe realizar cuando isLoginClicked es verdadero
    LaunchedEffect(isLoginClicked) {
        if (isLoginClicked) {
            val user = User(email = email, password = password)
            authViewModel.authenticate(user)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.h3)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Olvidaste tu contraseña?",
            color = MaterialTheme.colors.primary,
            modifier = Modifier.clickable {
                navController.navigate(Destinations.ChangePasswordScreen.route)
            }.padding(8.dp), textDecoration = TextDecoration.Underline, textAlign = TextAlign.End
        )

        Button(
            onClick = {
                if (!isLoginClicked) {  // Verificamos que no se haga clic mientras ya estamos autenticando
                    isLoginClicked = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoginClicked
        ) {
            Text("Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "¿No tenés cuenta? Registrate",
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body2.copy(textDecoration = TextDecoration.Underline),
            modifier = Modifier.clickable { navController.navigate(Destinations.RegisterScreen.route) }
        )

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
                Text(text = message, color = MaterialTheme.colors.error)
            }
        )
    }
}


