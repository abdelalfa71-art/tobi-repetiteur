package com.repetiteur.app

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.repetiteur.app.ui.NavigationApp

class MainActivity : ComponentActivity() {

    private val demandePermissions =
        registerForActivityResult(ActivityResul
