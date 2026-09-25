package com.repetiteur.app.ui.parametres

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

enum class ModeEcriture { CLAVIER, ARDOISE }

class Parametres(context: Context) {
    private val prefs = context.getSharedPreferences("parametres_repetiteur", Context.MODE_PRIVATE)

    var modeEcriture: ModeEcriture
        get() = ModeEcriture.valueOf(prefs.getString("mode_ecriture", ModeEcriture.ARDOISE.name)!!)
        set(value) = prefs.edit().putString("mode_ecriture", value.name).apply()

    var ecouteContinue: Boolean
        get() = prefs.getBoolean("ecoute_continue", true)
        set(value) = prefs.edit().putBoolean("ecoute_continue", value).apply()
}

@Composable
fun EcranParametres(onRetour: () -> Unit)
