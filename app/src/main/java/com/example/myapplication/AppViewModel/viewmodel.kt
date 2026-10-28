package com.example.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.LoteAlimentoDto
import com.example.myapplication.data.LoteRepository
import com.example.myapplication.data.UsuarioDto
import kotlinx.coroutines.launch

data class Notificacion(
    val titulo: String,
    val mensaje: String
)

class AppViewModel : ViewModel() {
    private val repository = LoteRepository()

    var userName by mutableStateOf("Usuario")

    val lotesDisponibles = mutableStateListOf<LoteAlimentoDto>()

    val notificaciones = mutableStateListOf<Notificacion>(
        Notificacion(
            titulo = "Usuario creado",
            mensaje = "La creación de tu usuario se realizó con éxito."
        ),
        Notificacion(
            titulo = "Tu solicitud ha sido aceptada",
            mensaje = "El lote de frutas y verduras ha sido aceptado por la organización."
        ),
        Notificacion(
            titulo = "Nuevo lote disponible",
            mensaje = "Hay un nuevo lote disponible (caramelos y gaseosas); ¡ve y separa el tuyo!"
        )
    )

    fun agregarNotificacion(titulo: String, mensaje: String) {
        notificaciones.add(index = 0, element = Notificacion(titulo, mensaje))
    }

    fun cargarLotes() {
        viewModelScope.launch {
            val resultado = repository.obtenerLotes()
            lotesDisponibles.clear()
            lotesDisponibles.addAll(resultado)
        }
    }

    fun registrarUsuario(usuario: UsuarioDto, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val exito = repository.registrarUsuario(usuario)
            if (exito) {
                agregarNotificacion(
                    titulo = "Usuario creado",
                    mensaje = "La creación de tu usuario se realizó con éxito."
                )
                onSuccess()
            }
        }
    }
}