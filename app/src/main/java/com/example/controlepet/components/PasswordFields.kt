package com.example.controlepet.components

 
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun PasswordFields (
    password: String,
    repeatPassword: String,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    repeat: Boolean = false
){
    var passwordVisible by remember { mutableStateOf(false) }
    var repeatPasswordVisible by remember { mutableStateOf(false) }
    var showMismatchError by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = password,
            onValueChange = {
                onPasswordChange(it)
                showMismatchError = repeatPassword.isNotEmpty() && repeatPassword != it
            },
            label = { Text(text = "Senha") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            placeholder = { Text(text = "Senha", Modifier.alpha(0.3f)) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha"
                    )
                }
            },
            isError = showMismatchError,
            modifier = Modifier.fillMaxWidth(),
        )

        if(repeat) {
            OutlinedTextField(
                value = repeatPassword,
                onValueChange = {
                    onRepeatPasswordChange(it)
                    showMismatchError = password.isNotEmpty() && password != it
                },
                label = { Text(text = "Repita a senha ") },
                visualTransformation = if (repeatPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                placeholder = { Text(text = "Repita a Senha", Modifier.alpha(0.3f)) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { repeatPasswordVisible = !repeatPasswordVisible }) {
                        Icon(
                            imageVector = if (repeatPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (repeatPasswordVisible) "Ocultar senha" else "Mostrar senha"
                        )
                    }
                },
                isError = showMismatchError,
                modifier = Modifier.fillMaxWidth(),
            )

            if (showMismatchError) {
                Text(
                    text = "As senhas não coincidem",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
