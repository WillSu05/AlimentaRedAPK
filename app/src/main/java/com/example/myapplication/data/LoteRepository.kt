package com.example.myapplication.data

import android.util.Log

class LoteRepository {


    suspend fun obtenerLotes(): List<LoteAlimentoDto> {
        return try {
            RetrofitClient.apiService.listarLotesDisponibles()
        } catch (e: Exception) {
            Log.e("LoteRepository", "Error al obtener lotes: ${e.localizedMessage}")
            emptyList()
        }
    }

    suspend fun publicarLote(lote: LoteAlimentoDto): Boolean {
        return try {
            RetrofitClient.apiService.publicarLote(lote)
            true
        } catch (e: Exception) {
            Log.e("LoteRepository", "Error al publicar lote: ${e.localizedMessage}")
            false
        }
    }


    suspend fun registrarUsuario(usuario: UsuarioDto): Boolean {
        return try {

            RetrofitClient.apiService.registrarUsuario(usuario)
            true
        } catch (e: Exception) {
            Log.e("LoteRepository", "Error al registrar usuario: ${e.localizedMessage}")
            false
        }
    }
}