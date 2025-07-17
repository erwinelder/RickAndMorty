package cz.ackee.testtask.rm.core.di

import cz.ackee.testtask.rm.character.di.characterModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(coreModule, characterModule)
    }
}