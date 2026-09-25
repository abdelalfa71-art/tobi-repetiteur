package com.repetiteur.app.audio

import android.content.Context
import android.content.Intent
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import java.util.Locale

class LecteurVocal(private val context: Context) {

    private var recognizer: SpeechRecognizer? = null

    fun ecouter(
        onResultat: (texteReconnu: String) -> Unit,
        onErreur: (message: String) -> Unit,
        onDebut: () -> Unit = {}
    ) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            onErreur("Reconnaissance vocale indisponible sur cet appareil")
            return
