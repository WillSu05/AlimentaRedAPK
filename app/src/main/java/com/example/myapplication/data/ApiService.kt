package com.example.myapplication.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// --- DTOs ---

data class UsuarioDto(
    val id: Long? = null,
    val nombre: String,
    val email: String,
    val password: String,
    val rol: String // Obligatorio en tu entidad Java
)

// 2. Lote de Alimento (Coincide con LoteAlimento.java)
data class LoteAlimentoDto(
    val id: Long? = null,
    val nombre: String,
    val cantidad: Double,
    val unidad: String,          // Ej: "Kg", "Litros", "Unidades"
    val fechaLimite: String,     // Formato "yyyy-MM-dd"
    val estado: String = "DISPONIBLE",
    val categoriaId: Long,       // Relación con CategoriaAlimento
    val donanteId: Long          // Relación con Usuario (Donante)
)

// 3. Solicitud de Donación (Coincide con SolicitudDonacion.java)
data class SolicitudDonacionDto(
    val id: Long? = null,
    val cantidadSolicitada: Double,
    val fechaSolicitud: String,  // Formato "yyyy-MM-dd'T'HH:mm:ss"
    val estado: String = "PENDIENTE",
    val loteId: Long,            // Relación con LoteAlimento
    val organizacionId: Long     // Relación con Organizacion
)

// 4. Entrega (Coincide con Entrega.java)
data class EntregaDto(
    val id: Long? = null,
    val fechaRetiro: String,     // Formato "yyyy-MM-dd'T'HH:mm:ss"
    val fechaRecepcion: String,  // Formato "yyyy-MM-dd'T'HH:mm:ss"
    val observaciones: String?,
    val asignacionId: Long       // Relación con Asignacion
)
data class CategoriaDto(
    val id: Long? = null,
    val nombre: String,
    val descripcion: String
)

    interface ApiService {

        @POST("api/usuarios/registrarUsuario")
        suspend fun registrarUsuario(@Body usuario: UsuarioDto): UsuarioDto

        @GET("api/usuarios/listarUsuario")
        suspend fun listarUsuarios(): List<UsuarioDto>

        @GET("api/usuarios/buscarUsuario/{id}")
        suspend fun buscarUsuario(@Path("id") id: Long): UsuarioDto

        @POST("api/lotes/publicarLote")
        suspend fun publicarLote(@Body lote: LoteAlimentoDto): LoteAlimentoDto

        @GET("api/lotes/disponibles")
        suspend fun listarLotesDisponibles(): List<LoteAlimentoDto>

        @GET("api/lotes/buscarLote/{id}")
        suspend fun buscarLote(@Path("id") id: Long): LoteAlimentoDto

        @POST("api/solicitudes/crearSolicitud")
        suspend fun crearSolicitud(@Body solicitud: SolicitudDonacionDto): SolicitudDonacionDto

        @POST("api/entregas/confirmar")
        suspend fun confirmarEntrega(@Body entrega: EntregaDto): EntregaDto
        @GET(value = "api/categorias/listarCategoria")
        suspend fun listarCategorias(): List<CategoriaDto>
}