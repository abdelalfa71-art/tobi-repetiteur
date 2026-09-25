package com.repetiteur.app.ui.mascotte

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.repetiteur.app.R

enum class EtatMascotte { REPOS, EXPLAINING, LISTENING, CELEBRATE, ENCOURAGING, THINKING }

@Composable
fun Mascotte(etat: EtatMascotte, modifier: Modifier
