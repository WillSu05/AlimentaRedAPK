package com.example.myapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun SolicitarLoteScreen(
    onBackClick: () -> Unit,
    onDetalleClick: () -> Unit
) {
    var busqueda by remember { mutableStateOf("") }

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
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Lotes Disponibles",
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

        TextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            placeholder = { Text("Buscar por categoría", color = Color.DarkGray) },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null, tint = Color.DarkGray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFA5D6A7),
                unfocusedContainerColor = Color(0xFFA5D6A7),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            LoteItemCard(
                categoria = "Frutas y verduras",
                estado = "Disponible",
                esDisponible = true,
                iconRes = R.drawable.frutas_verduras,
                onClick = onDetalleClick
            )
            LoteItemCard(
                categoria = "Lacteos",
                estado = "Disponible",
                esDisponible = true,
                iconRes = R.drawable.lacteos,
                onClick = onDetalleClick
            )
            LoteItemCard(
                categoria = "Panadería",
                estado = "No disponible",
                esDisponible = false,
                iconRes = R.drawable.panaderia,
                onClick = {}
            )
            LoteItemCard(
                categoria = "Comida Preparada",
                estado = "Disponible",
                esDisponible = true,
                iconRes = R.drawable.comida_preparada,
                onClick = onDetalleClick
            )
        }
    }
}

@Composable
fun LoteItemCard(
    categoria: String,
    estado: String,
    esDisponible: Boolean,
    iconRes: Int,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFA5D6A7)),
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clickable(enabled = esDisponible, onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = categoria,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = estado,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (esDisponible) Color(0xFF1B5E20) else Color.DarkGray
                )
            }
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}