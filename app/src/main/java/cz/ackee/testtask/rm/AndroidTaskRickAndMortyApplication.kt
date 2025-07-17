package cz.ackee.testtask.rm

import android.app.Application
import cz.ackee.testtask.rm.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class AndroidTaskRickAndMortyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@AndroidTaskRickAndMortyApplication)
        }
    }

}