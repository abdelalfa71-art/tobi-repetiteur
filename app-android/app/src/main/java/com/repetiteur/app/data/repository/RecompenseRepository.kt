package com.repetiteur.app.data.repository

import com.repetiteur.app.data.remote.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.serialization.Serializable

@Serializable
data class Cadeau(val id: String, val nom: String, val emoji: String, val rarete: String)

@Serializable
data class Recompenses(val enfant_id: String, val etoiles: Int = 0, val serie_jours: Int = 0)

class RecompenseRepository {

    private val db = SupabaseClient.client

    suspend fun getRecompenses(enfantId: String): Recompenses =
        db.from("recompenses_enfant")
            .select { filter { eq("enfant_id", enfantId) } }
            .decodeSingleOrNull() ?: Recompenses(enfantId)

    suspend fun ajouterEtoiles(enfantId: String, nombre: Int) {
        val actuel = getRecompenses(enfantId)
        db.
