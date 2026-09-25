package com.repetiteur.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Niveau(
    val id: String,
    val code: String,
    val nom: String,
    val ordre: Int
)

@Serializable
data class Matiere(
    val id: String,
    val code: String,
    val nom: String
)

@Serializable
data class Lecon(
    val id: String,
    val niveau_id: String,
    val matiere_id: Stri
