package com.zako.webetu.di

import com.zako.webetu.auth.login.di.loginModule
import com.zako.webetu.auth.user.di.userModule
import com.zako.webetu.databaseModule
import com.zako.webetu.ktorModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            ktorModule,
            databaseModule,
            loginModule,
            userModule
        )
    }
}
