package com.repetiteur.app.data.repository

import io.github.jan.supabase.functions.functions
import com.repetiteur.app.data.local.BaseLocale
import com.repetiteur.app.data.local.ExerciceCache
import com.repetiteur.app.data.local.LeconCache
import com.repetiteur.app.data.model.Exercice
import com.repetiteur.app.data.model.Lecon
import com.repetiteur.app.data.remote.SupabaseClient
import com.repetiteur.app.reseau.MoniteurReseau
import io.github.jan.supabase.postgrest.from
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LeconRepository(
    private val baseLocale: BaseLocale,
    private val moniteurReseau: MoniteurReseau
) {
    private val db = SupabaseClient.client

    suspend fun getLeconsParNiveauEtMatiere(niveauId: String, matiereId: String): List<Lecon> {
        if (moniteurReseau.estConnecteMaintenant()) {
            try {
                val lecons = db.from("lecons")
                    .select {
                        filter { eq("niveau_id", niveauId); eq("matiere_id", matiereId) }
                        order("semaine", ascending = true)
                    }
                    .decodeList
