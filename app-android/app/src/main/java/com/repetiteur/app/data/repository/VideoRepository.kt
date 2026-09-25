package com.repetiteur.app.data.repository

import com.repetiteur.app.data.local.BaseLocale
import com.repetiteur.app.data.local.VideoCache
import com.repetiteur.app.data.remote.SupabaseClient
import io.github.jan.supabase.functions.functions
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable data class AutrePersonnage(val qui: String, val action: String = "parle", val replique: String? = null)
@Serializable data class ElementVideo(val emoji: String, val nombre: Int = 1, val action: String = "apparait")
@Serializable data class SceneVideo(
    val decor: String = "marche",
    val tobi: String = "explique",
    val autre: AutrePersonnage? = null,
    val narration: String,
    val texte_ecran: String? = null,
    val elements: List<ElementVideo> = emptyList()
)
@Serializable data class ScriptVideo(val titre: String, val scenes: List<SceneVideo>)

class VideoRepository(private val baseLocale: BaseLocale) {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun obtenir(
        leconId: String, notion: String, contenuTexte: String, niveauNom: String,
        prenomEnfant: String, questionEnAttente: String?
    ): ScriptVideo? {
        val cle = "$leconId|$notion"
        baseLocale.videoDao().parCle(cle)?.let { return json.decodeFromString(it.scriptJson) }
        return try {
            val brut = SupabaseClient.client.functions.invoke(
                function = "generer-video",
                body = mapOf(
                    "lecon_id" to leconId, "notion" to notion, "contenu_texte" to contenuTexte,
                    "niveau_nom" to niveauNom, "prenom_enfant" to prenomEnfant,
                    "question_en_attente" to questionEnAttente
                )
            ).bodyAsText()
            val script = json.decodeFromString<ScriptVideo>(brut)
            if (questionEn
