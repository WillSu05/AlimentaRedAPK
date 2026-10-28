package com.example.myapplication.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// --- DTOs ---

data class UsuarioDto(
    val id: Long?,
    val nombre: String,
    val email: String,
    val password: String,
    val rol: String
)

data class LoteAlimentoDto(
    val id: Long?,
    val categoria: String,
    val descripcion: String,
    val cantidad: String,
    val ciudad: String
)

data class SolicitudDonacionDto(
    val id: Long?,
    val loteId: Long,
    val usuarioId: Long,
    val estado: String
)

data class EntregaDto(
    val id: Long?,
    val loteId: Long,
    val responsable: String,
    val fechaEntrega: String,
    val observaciones: String
)

// --- Interfaz de Endpoints ---

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
}