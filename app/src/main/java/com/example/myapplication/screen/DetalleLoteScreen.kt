package com.example.myapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun DetalleLoteScreen(
    onBackClick: () -> Unit,
    onRequestSuccess: () -> Unit
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(onClick = onBackClick)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = Color.Black)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Detalle del lote",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Image(
                painter = painterResource(id = R.drawable.logo_alimentared),
                contentDescription = "Logo",
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE8F5E9)),
            contentAlignment = Alignment.Center
        ) {
            Text("Imagen del Lote", fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = "Frutas y verduras", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = "Restaurante", fontSize = 13.sp, color = Color.Gray)
            Text(text = "Bogotá", fontSize = 13.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Cantidad: 10 kg", fontSize = 13.sp, fontWeight = FontWeight.Medium)
            Text(text = "Fecha límite: 10-12-2026", fontSize = 13.sp, fontWeight = FontWeight.Medium)
            Text(text = "Categoría: Frutas", fontSize = 13.sp, fontWeight = FontWeight.Medium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFA5D6A7))
                .padding(16.dp)
        ) {
            Column {
                Text(text = "Descripción:", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "La mejor calidad en frutas y hortalizas, del campo a tu mesa", fontSize = 12.sp, color = Color.DarkGray)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onRequestSuccess,
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA5D6A7)),
            modifier = Modifier
                .width(160.dp)
                .height(45.dp)
        ) {
            Text(text = "Solicitar lote", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}