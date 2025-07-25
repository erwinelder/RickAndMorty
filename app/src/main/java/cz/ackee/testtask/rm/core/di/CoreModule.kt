package cz.ackee.testtask.rm.core.di

import cz.ackee.testtask.rm.core.data.local.db.AppDatabase
import cz.ackee.testtask.rm.core.presentation.navigation.viewmodel.NavViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val coreModule = module {

    /* ---------- Data layer ---------- */

    single<AppDatabase> {
        AppDatabase.getDatabase(context = get())
    }

    single<HttpClient> {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }

    /* ---------- View Models ---------- */

    viewModel {
        NavViewModel()
    }

}