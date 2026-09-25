package com.repetiteur.app.data.local

import androidx.room.*

@Entity(tableName = "lecons_cache")
data class LeconCache(
    @PrimaryKey val id: String,
    val niveauId: String,
    val matiereId: String,
    val titre: String,
    val contenuTexte: String?,
    val audioUrlLocal: String?
)

@Entity(tableName = "exercices_cache")
data class ExerciceCache(
    @PrimaryKey val id: String,
    val leconId: String,
    val type: String,
    val question: String,
    val choixJson:
