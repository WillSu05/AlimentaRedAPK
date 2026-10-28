package com.example.myapplication.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

val VerdeClaroCard = Color(0xFFA5D6A7)
val VerdeOscuroFondo = Color(0xFF2E7D32)

@Composable
fun LoginScreen(
    onLoginClick: (String) -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo_alimentared), contentDescription = "Logo", modifier = Modifier.size(100.dp))
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "AlimentaRed", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = VerdeOscuroFondo)
        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Ingrese su usuario o correo", color = Color.DarkGray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.colors(focusedContainerColor = VerdeClaroCard, unfocusedContainerColor = VerdeClaroCard, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Ingrese su contraseña", color = Color.DarkGray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(focusedContainerColor = VerdeClaroCard, unfocusedContainerColor = VerdeClaroCard, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val name = if (email.isNotBlank()) email.substringBefore("@") else "Usuario"
                onLoginClick(name)
            },
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VerdeOscuroFondo),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(text = "Iniciar sesion", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onRegisterClick,
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VerdeOscuroFondo),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(text = "Crear cuenta", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}
