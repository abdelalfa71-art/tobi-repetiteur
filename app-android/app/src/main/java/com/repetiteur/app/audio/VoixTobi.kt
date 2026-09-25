package com.repetiteur.app.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.util.Locale
import java.util.UUID
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class VoixTobi(context: Context) {

    var prononciationPrenom: Pair<String, String>? = null

    private var pret = false
    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.FRENCH
                pret = true
            }
        }
    }

    fun parler(texte: String, onDebut: () -> Unit, onFin: () -> Unit) {
        val moteur = tts
        if (moteur == null || !pret) { onFin(); return }

        moteur.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) = onDebut()
            override fun onDone(utteranceId: String?) = onFin()
            override fun onError(utteranceId: String?) = onFin()
        })

        val aDire = prononciationPrenom?.let { (ecrit, oral) -> texte.replace(ecrit, oral) } ?: texte
        moteur.speak(aDire, TextToSpeech.QUEUE_FLUSH, null, UUID.randomUUID().toString())
    }

    suspend fun direEtAttendre(texte: String) = suspendCancellableCoroutine<Unit> { cont ->
        parler(texte, onDebut = {}, onFin = { if (cont.isActive) cont.resume(Unit) })
        cont.invokeOnCancellation { arreter() }
    }

    private var phrasesEnCours = 0
    private var fluxTermine = true
    private var onFinFlux: (() -> Unit)? = null

    fun commencerFlux(onFin: () -> Unit) {
        phrasesEnCours = 0
        fluxTermine = false
        onFinFlux = onFin
        tts?.stop()
        tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {}
            override fun onDone(utteranceId: String?) = phraseFinie()
            override fun onError(utteranceId: String?) = phraseFinie()
        })
    }

    fun ajouter(phrase: String) {
        val moteur = tts ?: return
        if (!pret) return
        phrasesEnCours++
        val aDire = prononciationPrenom?.let { (ecrit, oral) -> phrase.replace(ecrit, oral) } ?: phrase
        moteur.speak(aDire, TextToSpeech.QUEUE_ADD, null, UUID.randomUUID().toString())
    }

    fun terminerFlux() {
        fluxTermine = true
        if (phrasesEnCours <= 0) finirFlux()
    }

    @Synchronized private fun phraseFinie() {
        phrasesEnCours--
        if (fluxTermine && phrasesEnCours <= 0) finirFlux()
    }

    private fun finirFlux() {
        val f = onFinFlux; onFinFlux = null
        android.os.Handler(android.os.Looper.getMainLooper()).post { f?.invoke() }
    }

    fun arreter() {
        tts?.stop()
    }

    fun liberer() {
        tts?.shutdown()
    }
}
