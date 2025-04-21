package com.zako.webetu.navigation.di

import com.zako.webetu.navigation.presentation.bottomNavigationBar.MainGraphViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val navigationModule = module {
    viewModel {
        MainGraphViewModel()
    }
}