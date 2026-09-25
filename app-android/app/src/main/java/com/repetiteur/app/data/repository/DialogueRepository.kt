package com.repetiteur.app.data.repository

import io.github.jan.supabase.functions.functions
import com.repetiteur.app.data.remote.SupabaseClient
import io.github.jan.supabase.postgrest.from
import com.repetiteur.app.BuildConfig
import io.github.jan.supabase.gotrue.auth
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.header
import io.ktor.client.request.preparePost
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsChannel
import io.ktor.utils.io.readUTF8Lin
