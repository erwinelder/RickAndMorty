package cz.ackee.testtask.rm.di

import cz.ackee.testtask.rm.core.presentation.navigation.viewmodel.NavViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val coreModule = module {

    /* ---------- View Models ---------- */

    viewModel {
        NavViewModel()
    }

}