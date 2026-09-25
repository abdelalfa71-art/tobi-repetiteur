package com.repetiteur.app.ui.lecons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.repetiteur.app.data.model.Lecon
import com.repetiteur.app.data.repository.LeconRepository
import com.repetiteur.app.ui.mascotte.EtatMascotte
import com.repetiteur.app.ui.mascotte.Mascotte

@Composable
fun EcranLecons(
    niveauId: String,
    matiereId: String,
    nomMatiere: String,
    repository: LeconRepository,
    onLeconChoisie: (Lecon) -> Unit,
    onCapturer: () -> Unit
) {
    var lecons by r
