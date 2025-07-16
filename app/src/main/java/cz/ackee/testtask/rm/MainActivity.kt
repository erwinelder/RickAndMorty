package cz.ackee.testtask.rm

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.BoxWithConstraints
import cz.ackee.testtask.rm.core.presentation.component.screenContainer.MainScaffold
import cz.ackee.testtask.rm.core.presentation.theme.AndroidTaskRickAndMortyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            BoxWithConstraints {
                AndroidTaskRickAndMortyTheme(
                    boxWithConstraintsScope = this
                ) {
                    MainScaffold()
                }
            }
        }
    }
}