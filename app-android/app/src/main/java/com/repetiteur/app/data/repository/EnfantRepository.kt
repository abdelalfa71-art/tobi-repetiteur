package com.repetiteur.app.data.repository

import com.repetiteur.app.data.model.Enfant
import com.repetiteur.app.data.model.Matiere
import com.repetiteur.app.data.model.Niveau
import com.repetiteur.app.data.remote.SupabaseClient
import io.github.jan.supabase.postgrest.from

class EnfantRepository {

    private val db = SupabaseClient.client

    suspend fun getEnfantsDuParent(parentId: String): List<Enfan
