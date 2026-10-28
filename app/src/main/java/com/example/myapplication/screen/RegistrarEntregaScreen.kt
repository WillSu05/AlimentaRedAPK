package com.example.myapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun RegistrarEntregaScreen(
    categoriaLote: String, // Recibe el nombre del lote seleccionado
    iconRes: Int,          // Recibe el icono específico del lote
    onBackClick: () -> Unit,
    onEntregaSuccess: () -> Unit
) {
    var responsable by remember { mutableStateOf("") }
    var fechaEntrega by remember { mutableStateOf("") }
    var observaciones by remember { mutableStateOf("") }

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
                    text = "Registrar entrega",
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

        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFA5D6A7)),
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "Lote: $categoriaLote",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = "Restaurante / Donante", fontSize = 12.sp, color = Color.DarkGray)
                    Text(text = "Bogotá D.C.", fontSize = 12.sp, color = Color.DarkGray)
                }
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        CampoFormulario(value = responsable, onValueChange = { responsable = it }, placeholder = "Responsable")
        Spacer(modifier = Modifier.height(12.dp))
        CampoFormulario(value = fechaEntrega, onValueChange = { fechaEntrega = it }, placeholder = "Fecha de entrega")
        Spacer(modifier = Modifier.height(12.dp))
        CampoFormulario(value = observaciones, onValueChange = { observaciones = it }, placeholder = "Observaciones")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onEntregaSuccess,
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA5D6A7)),
            modifier = Modifier
                .width(180.dp)
                .height(45.dp)
        ) {
            Text(text = "Registrar entrega", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}