package com.jason.littlelives

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jason.littlelives.ui.feature.root.AppNavigation
import com.jason.littlelives.ui.feature.root.AppNavigator
import com.jason.littlelives.ui.theme.LittlelivesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittlelivesTheme(
                dynamicColor = false
            ) {
                val systemUiController = rememberSystemUiController()
                val darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
                val backgroundColor = MaterialTheme.colorScheme.surface

                SideEffect {
                    // Update all of the system bar colors to be transparent, and use
                    // dark icons if we're in light theme
                    systemUiController.setStatusBarColor(
                        color = backgroundColor,
                        darkIcons = darkIcons
                    )

                    // setStatusBarColor() and setNavigationBarColor() also exist
                    systemUiController.setNavigationBarColor(
                        color = backgroundColor
                    )
                }
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation(navigator = navigator)
                }
            }
        }
    }
}