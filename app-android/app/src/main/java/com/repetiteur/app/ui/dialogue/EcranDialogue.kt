package com.repetiteur.app.ui.dialogue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.repetiteur.app.audio.EcouteContinue
import com.repetiteur.app.audio.LecteurVocal
import com.repetiteur.app.audio.VoixTobi
import com.repetiteur.app.data.repository.DialogueRepository
import com.repetiteur.app.data.repository.MessageDialogue
import com.repetiteur.app.reseau.MoniteurReseau
import com.repetiteur.app.data.repository.Cadeau
import com.repetiteur.app.data.repository.RecompenseRepository
import com.repetiteur.app.ui.amusement.CadeauSurprise
import com.repetiteur.app.ui.amusement.DanseTobi
import com.repetiteur.app.ui.ardoise.Ardoise
import com.repetiteur.app.data.repository.ScriptVideo
import com.repetiteur.app.data.repository.VideoRepository
import com.repetiteur.app.ui.video.LecteurVideo
import com.repetiteur.app.ui.parametres.ModeEcriture
import com.repetiteur.app.ui.parametres.Parametres
import com.repetiteur.app.ui.mascotte.EtatMascotte
import com.repetiteur.app.ui.mascotte.Mascotte
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EcranDialogue(
    enfantId: String,
    leconId: String,
    matiereId: String,
    titreLecon: String,
    contenuTexte: String,
    prenomEnfant: String,
    prononciationPrenom: String? = null,
    niveauNom: String = "",
    videos: VideoRepository? = null,
    repository: DialogueRepository = remember { DialogueRepository() },
    recompenses: RecompenseRepository = remember { RecompenseRepository() }
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val lecteurVocal = remember { LecteurVocal(context) }
    val prononciationOrale = prononciationPrenom
    val voixTobi = remember {
        VoixTobi(context).apply { prononciationPrenom = prononciationOrale?.let { prenomEnfant to it } }
    }
    val listState = rememberLazyListState()
    val modeEcriture = remember { Parametres(context).modeEcriture }
    val ecouteActivee = remember { Parametres(context).ecouteContinue }
    val ecoute = remember { EcouteCon
