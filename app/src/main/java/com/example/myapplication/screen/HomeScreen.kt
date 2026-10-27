package com.example.prestamo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

val VerdeClaroCard = Color(0xFFC8E6C9)
val VerdeOscuroFondo = Color(0xFF2E7D32)

@Composable
fun HomeScreen(
    userName: String = "Usuario",
    onPublicarLoteClick: () -> Unit,
    onSolicitarLoteClick: () -> Unit,
    onMisSolicitudesClick: () -> Unit,
    onNotificacionesClick: () -> Unit,
    onPerfilClick: () -> Unit
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
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_alimentared),
                contentDescription = "Logo",
                modifier = Modifier.size(45.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(VerdeOscuroFondo)
                .padding(20.dp)
        ) {
            Column {
                Text(
                    text = "Hola -$userName-",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "¿Qué deseas hacer hoy?",
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MenuCard(
                title = "Publicar lote",
                iconRes = R.drawable.publicar_lote,
                onClick = onPublicarLoteClick
            )
            MenuCard(
                title = "Solicitar lote",
                iconRes = R.drawable.solicitar_lote,
                onClick = onSolicitarLoteClick
            )
        }

        Spacer(modifier = Modifier.height(20.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MenuCard(
                title = "Mis solicitudes",
                iconRes = R.drawable.mis_solicitudes,
                onClick = onMisSolicitudesClick
            )
            MenuCard(
                title = "Notificaciones",
                iconRes = R.drawable.notificaciones,
                onClick = onNotificacionesClick
            )
        }

        Spacer(modifier = Modifier.height(30.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(VerdeClaroCard)
                .clickable(onClick = onPerfilClick)
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Perfil",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Ingresar a mi perfil",
                        fontSize = 13.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Composable
fun MenuCard(title: String, iconRes: Int, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = VerdeClaroCard),
        modifier = Modifier
            .size(150.dp, 130.dp)
            .clickable(onClick = onClick)
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
                modifier = Modifier.size(45.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
    }
}