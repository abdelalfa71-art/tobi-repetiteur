package com.repetiteur.app

import android.content.Context
import androidx.room.Room
import com.repetiteur.app.data.local.BaseLocale
import com.repetiteur.app.data.model.Matiere
import com.repetiteur.app.data.model.Niveau
import com.repetiteur.app.data.repository.EnfantRepository
import com.repetiteur.app.data.repository.LeconRepository
import com.repetiteur.app.data.repository.VideoRepository
import com.repetiteur.app.reseau.MoniteurReseau
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AppContainer(context: Context) {
    val baseLocale: BaseLoca
