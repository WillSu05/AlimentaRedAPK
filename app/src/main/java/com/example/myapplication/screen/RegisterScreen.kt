package com.example.myapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.AppViewModel
import com.example.myapplication.R
import com.example.myapplication.data.UsuarioDto

@Composable
fun RegisterScreen(
    onRegisterSuccess: (String) -> Unit,
    onBackToLoginClick: () -> Unit,
    viewModel: AppViewModel = viewModel()
) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_alimentared),
                contentDescription = "Logo",
                modifier = Modifier.size(45.dp)
            )
        }

        Text(
            text = "Crear cuenta",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {},
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA5D6A7))
        ) {
            Text(text = "Donante", color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        CampoFormulario(
            value = nombre,
            onValueChange = { nombre = it },
            placeholder = "Nombre completo"
        )
        Spacer(modifier = Modifier.height(12.dp))
        CampoFormulario(
            value = email,
            onValueChange = { email = it },
            placeholder = "Correo electrónico"
        )
        Spacer(modifier = Modifier.height(12.dp))
        CampoFormulario(
            value = password,
            onValueChange = { password = it },
            placeholder = "Contraseña",
            isPassword = true
        )
        Spacer(modifier = Modifier.height(12.dp))
        CampoFormulario(
            value = telefono,
            onValueChange = { telefono = it },
            placeholder = "Teléfono"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val nuevoUsuario = UsuarioDto(
                    id = null,
                    nombre = nombre,
                    email = email,
                    password = password,
                    rol = "DONANTE" // Campo obligatorio requerido por la entidad de Spring Boot
                )
                viewModel.registrarUsuario(nuevoUsuario) {
                    onRegisterSuccess(if (nombre.isNotBlank()) nombre else "Usuario")
                }
            },
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA5D6A7)),
            modifier = Modifier
                .width(160.dp)
                .height(45.dp)
        ) {
            Text(text = "Registrarse", color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))
        TextButton(onClick = onBackToLoginClick) {
            Text(text = "¿Ya tienes cuenta? Inicia sesión", color = Color(0xFF2E7D32))
        }
    }
}

@Composable
fun CampoFormulario(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color.DarkGray) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(50.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFA5D6A7),
            unfocusedContainerColor = Color(0xFFA5D6A7)
        ),
        singleLine = true
    )
}