package com.example.myapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
fun MisSolicitudesScreen(
    onHomeClick: () -> Unit
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
            Text(
                text = "Mis solicitudes",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Image(
                painter = painterResource(id = R.drawable.logo_alimentared),
                contentDescription = "Logo",
                modifier = Modifier.size(45.dp)
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

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoriaCard(
                    title = "Frutas y verduras",
                    iconRes = R.drawable.frutas_verduras,
                    modifier = Modifier.weight(1f)
                )
                CategoriaCard(
                    title = "Lácteos",
                    iconRes = R.drawable.lacteos,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoriaCard(
                    title = "Panadería",
                    iconRes = R.drawable.panaderia,
                    modifier = Modifier.weight(1f)
                )
                CategoriaCard(
                    title = "Comida Preparada",
                    iconRes = R.drawable.comida_preparada,
                    modifier = Modifier.weight(1f)
                )
            }
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
fun CategoriaCard(title: String, iconRes: Int, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFA5D6A7)),
        modifier = modifier.height(140.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}