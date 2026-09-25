package com.repetiteur.app.data.repository

import io.github.jan.supabase.functions.functions
import com.repetiteur.app.data.model.Matiere
import com.repetiteur.app.data.remote.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.storage
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ContenuCapture(
    val id: String? = null,
    val enfant_id: String,
    val type_fichier: String,
    val url_fichier: String,
    val matiere_id: String? = null,
    val titre: String? = null,
    val contenu_extrait: String? = null,
    val statut: String = "en_attente"
)

@Serializable
data class ResultatAnalyse(
    val titre: String,
    val matiere_id: String,
    val contenu_extrait: String,
    val notes_alignement: String? = null,
    val lecon_id: String? = null
)

class CaptureRepository {

    private val db = SupabaseClient.client
    private val bucket = db.storage.from("captures-cahiers")

    suspend fun uploaderEtEnregistrer(
        enfantId: String,
        octets: ByteArray,
        extension: String,
        typeFichier: String
    ): ContenuCapture {
        val chemin = "$enfantId/${UUID.randomUUID()}.$extension"
        bucket.upload(chemin, octets)
        val url = bucket.createSignedUrl(chemin, expiresIn = kotlin.time.Duration.parse("PT1H"))

        return db.from("contenus_captures")
            .insert(
                ContenuCapture(
                    enfant_id = enfantId,
                    type_fichier = typeFichier,
                    url_fichier = url
                )
            ) { select() }
            .decodeSingle()
    }

    suspend fun analyser(capture: ContenuCapture, niveauNom: String, matieres: List<Matiere>): ResultatAnalyse {
        return db.functions.invoke(
            function = "analyser-capture",
            body = mapOf(
                "capture_id" to capture.id,
                "enfant_id" to capture.enfant_id,
                "url_fichier" to capture.url_fichier,
                "niveau_nom" to niveauNom,
                "liste_matieres" to matieres.map { mapOf("id" to it.id, "code" to it.code, "nom" to it.nom) }
            )
        ).decode()
    }

    fun leconDepuisCapture(resultat: ResultatAnalyse): String? = resultat.lecon_id
}
