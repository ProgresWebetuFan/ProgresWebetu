package com.zako.webetu

import com.zako.webetu.database.AppDatabase
import com.zako.webetu.database.getAppDatabaseBuilder
import com.zako.webetu.database.getWebetuInstance
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.Module
import org.koin.dsl.module

actual val ktorModule = module {
    single<HttpClient> {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}
actual val databaseModule: Module = module {
    single<AppDatabase> {
        getAppDatabaseBuilder(
            context = get()
        ).getWebetuInstance()
    }
}
