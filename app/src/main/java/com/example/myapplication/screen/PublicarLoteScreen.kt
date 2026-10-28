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

// PÁGINA 4: PUBLICAR LOTE
@Composable
fun PublicarLoteScreen(onBackClick: () -> Unit, onSubmitClick: () -> Unit) {
    var nombreLote by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(20.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable(onClick = onBackClick)) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null, tint = Color.Black)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Publicar lote", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
            Image(painter = painterResource(id = R.drawable.logo_alimentared), contentDescription = null, modifier = Modifier.size(40.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))
        CampoFormulario(value = nombreLote, onValueChange = { nombreLote = it }, placeholder = "Nombre del lote")
        Spacer(modifier = Modifier.height(10.dp))
        CampoFormulario(value = categoria, onValueChange = { categoria = it }, placeholder = "Categoría")
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(modifier = Modifier.weight(1f)) { CampoFormulario(value = cantidad, onValueChange = { cantidad = it }, placeholder = "Cantidad") }
            Box(modifier = Modifier.weight(1f)) { CampoFormulario(value = peso, onValueChange = { peso = it }, placeholder = "Peso (Kg)") }
        }
        Spacer(modifier = Modifier.height(10.dp))
        CampoFormulario(value = descripcion, onValueChange = { descripcion = it }, placeholder = "Descripción (Opcional)")

        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(onClick = onSubmitClick, shape = RoundedCornerShape(50.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA5D6A7)), modifier = Modifier.width(160.dp).height(45.dp)) {
                Text(text = "Publicar lote", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}