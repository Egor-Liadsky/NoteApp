package com.lyadsky.noteapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.lyadsky.noteapp.android.feature.mainNavigation.MainNavigationScreen
import com.lyadsky.noteapp.android.navigation.AndroidNavigator
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val rootNavigation: AndroidNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO get color from values
        window.statusBarColor = Color(0xFF194362).toArgb()
        setContent {
            val navController = rememberNavController()
            rootNavigation.navController = navController
            MainNavigationScreen(navController)
        }
    }
}
