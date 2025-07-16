package cz.ackee.testtask.rm

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import cz.ackee.testtask.rm.presentation.navigation.AppNavHost
import cz.ackee.testtask.rm.presentation.theme.AndroidTaskRickAndMortyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            BoxWithConstraints {
                AndroidTaskRickAndMortyTheme(
                    boxWithConstraintsScope = this
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { screenPadding ->
                        AppNavHost(
                            screenPadding = screenPadding
                        )
                    }
                }
            }
        }
    }
}