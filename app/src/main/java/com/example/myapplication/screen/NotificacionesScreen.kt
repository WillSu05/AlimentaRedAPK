package com.example.myapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun NotificacionesScreen(
    onHomeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.notificaciones),
                    contentDescription = null,
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Notificaciones",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Image(
                painter = painterResource(id = R.drawable.logo_alimentared),
                contentDescription = "Logo",
                modifier = Modifier.size(45.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            NotificacionCard(
                title = "Usuario creado",
                message = "La creación de tu usuario se realizó con éxito."
            )
            NotificacionCard(
                title = "Tu solicitud ha sido aceptada",
                message = "El lote de frutas y verduras ha sido aceptado por la organizacion."
            )
            NotificacionCard(
                title = "Nuevo lote",
                message = "Hay un nuevo lote disponible (caramelos y gaseosas) ¡ve y separa el tuyo!"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onHomeClick,
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA5D6A7)),
            modifier = Modifier
                .width(150.dp)
                .height(45.dp)
        ) {
            Text(text = "Inicio", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun NotificacionCard(title: String, message: String) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFA5D6A7)),
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = message,
                fontSize = 12.sp,
                color = Color.DarkGray
            )
        }
    }
}