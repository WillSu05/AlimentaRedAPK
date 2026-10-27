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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

val VerdeClaroInput = Color(0xFFA5D6A7)

@Composable
fun PublicarLoteScreen(
    onBackClick: () -> Unit,
    onSubmitClick: () -> Unit
) {
    var nombreLote by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var disponibilidad by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

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
                text = "Publicar lote",
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

        Spacer(modifier = Modifier.height(20.dp))

        CampoFormulario(label = "Nombre del lote", value = nombreLote, onValueChange = { nombreLote = it })

        Spacer(modifier = Modifier.height(10.dp))

        CampoFormulario(label = "Categoria", value = categoria, onValueChange = { categoria = it })

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Cantidad", fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(start = 8.dp, bottom = 4.dp))
                TextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = VerdeClaroInput,
                        unfocusedContainerColor = VerdeClaroInput,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Peso (Kg)", fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(start = 8.dp, bottom = 4.dp))
                TextField(
                    value = peso,
                    onValueChange = { peso = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = VerdeClaroInput,
                        unfocusedContainerColor = VerdeClaroInput,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        CampoFormulario(label = "Disponibilidad", value = disponibilidad, onValueChange = { disponibilidad = it })

        Spacer(modifier = Modifier.height(10.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Descripción (Opcional)", fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(start = 8.dp, bottom = 4.dp))
            TextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = VerdeClaroInput,
                    unfocusedContainerColor = VerdeClaroInput,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = onSubmitClick,
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VerdeClaroInput),
            modifier = Modifier
                .width(160.dp)
                .height(45.dp)
        ) {
            Text(text = "Publicar lote", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CampoFormulario(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(start = 8.dp, bottom = 4.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = VerdeClaroInput,
                unfocusedContainerColor = VerdeClaroInput,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
    }
}