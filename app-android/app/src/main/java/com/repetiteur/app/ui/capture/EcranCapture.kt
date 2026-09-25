package com.repetiteur.app.ui.capture

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.repetiteur.app.data.model.Matiere
import com.repetiteur.app.data.repository.CaptureRepository
import com.repetiteur.app.data.repository.ResultatAnalyse
import com.repetiteur.app.ui.mascotte.EtatMascotte
import com.repetiteur.app.ui.mascotte.Mascotte
import kotlinx.coroutines.launch

@Composable
fun EcranCapture(
    enf
