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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.AppViewModel
import com.example.myapplication.R
import com.example.myapplication.data.CategoriaDto
import com.example.myapplication.data.LoteAlimentoDto

// PÁGINA 4: PUBLICAR LOTE
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublicarLoteScreen(
    onBackClick: () -> Unit,
    onSubmitClick: () -> Unit,
    viewModel: AppViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    var nombreLote by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var fechaLimite by remember { mutableStateOf("") }

    // Variables para los menús
    var categoriaSeleccionada by remember { mutableStateOf<CategoriaDto?>(null) }
    var unidadSeleccionada by remember { mutableStateOf("") }

    var expandidoCategoria by remember { mutableStateOf(false) }
    var expandidoUnidad by remember { mutableStateOf(false) }

    // Variables para el Calendario (DatePicker)
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    val listaUnidades = listOf("Kg", "Litros", "Unidades")
    val context = androidx.compose.ui.platform.LocalContext.current

    // Carga las categorías desde la base de datos al abrir la pantalla
    LaunchedEffect(Unit) {
        viewModel.cargarCategorias()
    }

    // Modal del Calendario
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    val selectedDateMillis = datePickerState.selectedDateMillis
                    if (selectedDateMillis != null) {
                        // Formato compatible con java.util.Date y Spring Boot
                        val formatter = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", java.util.Locale.getDefault())
                        formatter.timeZone = java.util.TimeZone.getTimeZone("UTC") // Evita desfase de días
                        fechaLimite = formatter.format(java.util.Date(selectedDateMillis))
                    }
                }) {
                    Text("Aceptar", color = Color(0xFF2E7D32))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancelar", color = Color.Gray)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

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
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(modifier = Modifier.weight(1f)) {
                CampoFormulario(value = cantidad, onValueChange = { cantidad = it }, placeholder = "Cantidad")
            }
            Box(modifier = Modifier.weight(1f)) {
                ExposedDropdownMenuBox(expanded = expandidoUnidad, onExpandedChange = { expandidoUnidad = !expandidoUnidad }) {
                    TextField(
                        value = unidadSeleccionada,
                        onValueChange = {},
                        readOnly = true,
                        placeholder = { Text("Unidad", color = Color.DarkGray) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandidoUnidad) },
                        modifier = Modifier.menuAnchor().fillMaxWidth(),
                        shape = RoundedCornerShape(50.dp),
                        colors = TextFieldDefaults.colors(focusedContainerColor = Color(0xFFA5D6A7), unfocusedContainerColor = Color(0xFFA5D6A7))
                    )
                    ExposedDropdownMenu(expanded = expandidoUnidad, onDismissRequest = { expandidoUnidad = false }) {
                        listaUnidades.forEach { unidad ->
                            DropdownMenuItem(text = { Text(unidad) }, onClick = {
                                unidadSeleccionada = unidad
                                expandidoUnidad = false
                            })
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Menú dinámico de CATEGORÍAS (conectado a BD)
        ExposedDropdownMenuBox(expanded = expandidoCategoria, onExpandedChange = { expandidoCategoria = !expandidoCategoria }) {
            TextField(
                value = categoriaSeleccionada?.nombre ?: "",
                onValueChange = {},
                readOnly = true,
                placeholder = { Text("Seleccionar Categoría", color = Color.DarkGray) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandidoCategoria) },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.colors(focusedContainerColor = Color(0xFFA5D6A7), unfocusedContainerColor = Color(0xFFA5D6A7))
            )
            ExposedDropdownMenu(expanded = expandidoCategoria, onDismissRequest = { expandidoCategoria = false }) {
                if (viewModel.categorias.isEmpty()) {
                    DropdownMenuItem(text = { Text("Cargando categorías...") }, onClick = {})
                } else {
                    viewModel.categorias.forEach { categoria ->
                        DropdownMenuItem(
                            text = { Text(categoria.nombre) },
                            onClick = {
                                categoriaSeleccionada = categoria
                                expandidoCategoria = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Campo de FECHA LÍMITE interactivo
        Box(modifier = Modifier.clickable { showDatePicker = true }) {
            TextField(
                value = fechaLimite,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                placeholder = { Text("Fecha límite (YYYY-MM-DD)", color = Color.DarkGray) },
                modifier = Modifier.fillMaxWidth().clickable { showDatePicker = true },
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = Color(0xFFA5D6A7),
                    disabledTextColor = Color.Black
                )
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(
                onClick = {
                    android.util.Log.d("PublicarLote", "--- CLIC EN PUBLICAR LOTE ---")

                    // Forzamos el ID 1 por defecto si no se seleccionó ninguno del menú para evitar bloqueos
                    if (nombreLote.isBlank()) {
                        android.widget.Toast.makeText(context, "Ingresa el nombre del lote", android.widget.Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (cantidad.toDoubleOrNull() == null) {
                        android.widget.Toast.makeText(context, "Ingresa una cantidad válida", android.widget.Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (unidadSeleccionada.isBlank()) {
                        android.widget.Toast.makeText(context, "Selecciona una unidad de medida", android.widget.Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (fechaLimite.isBlank()) {
                        android.widget.Toast.makeText(context, "Selecciona una fecha límite", android.widget.Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val nuevoLote = LoteAlimentoDto(
                        nombre = nombreLote,
                        cantidad = cantidad.toDoubleOrNull() ?: 0.0,
                        unidad = unidadSeleccionada,
                        fechaLimite = fechaLimite,
                        categoriaId = categoriaSeleccionada?.id ?: 1L,
                        donanteId = 1L
                    )

                    android.util.Log.d("PublicarLote", "Enviando DTO al ViewModel: $nuevoLote")

                    viewModel.registrarNuevoLote(nuevoLote) {
                        android.util.Log.d("PublicarLote", "¡Lot registrado con éxito en Supabase! Regresando...")
                        onSubmitClick()
                    }
                },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA5D6A7)),
                modifier = Modifier.width(160.dp).height(45.dp)
            ) {
                Text(text = "Publicar lote", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}