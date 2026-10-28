package com.example.myapplication

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.CategoriaDto
import com.example.myapplication.data.LoteAlimentoDto
import com.example.myapplication.data.LoteRepository
import com.example.myapplication.data.RetrofitClient
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
                userName = usuario.nombre // Actualizamos el nombre de usuario reactivamente
                agregarNotificacion(
                    titulo = "Usuario creado",
                    mensaje = "La creación de tu usuario se realizó con éxito."
                )
                onSuccess()
            }
        }
    }

    val categorias = mutableStateListOf<CategoriaDto>()

    // Agrega esta función para cargarlas
    fun cargarCategorias() {
        viewModelScope.launch {
            val resultado = repository.obtenerCategorias()
            categorias.clear()
            categorias.addAll(resultado)
        }
    }
    // Método corregido para conectar con el repositorio y enviar el lote a la API
    fun registrarNuevoLote(nuevoLote: LoteAlimentoDto, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.publicarLote(nuevoLote)
                // Si llega aquí, se guardó con éxito en Supabase
                agregarNotificacion(
                    titulo = "Nuevo lote publicado",
                    mensaje = "Has publicado el lote '${nuevoLote.nombre}' exitosamente."
                )
                cargarLotes()
                onSuccess() // <-- Esto ejecuta el regreso al menú inicial automáticamente (popBackStack)
            } catch (e: Exception) {
                // AQUÍ IMPRIMIMOS EL ERROR REAL QUE MANDA EL BACKEND
                Log.e("AppViewModel", "DETALLE DEL ERROR 400: ${e.localizedMessage}")
                if (e is retrofit2.HttpException) {
                    val errorBody = e.response()?.errorBody()?.string()
                    Log.e("AppViewModel", "Cuerpo del error del servidor: $errorBody")
                }
            }
        }
    }
}