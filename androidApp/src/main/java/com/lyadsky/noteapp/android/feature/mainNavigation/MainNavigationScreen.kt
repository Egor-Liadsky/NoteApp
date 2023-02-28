package com.lyadsky.noteapp.android.feature.mainNavigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lyadsky.noteapp.android.di.ViewModelWrapper
import com.lyadsky.noteapp.android.feature.main.MainScreen
import com.lyadsky.noteapp.android.feature.profile.ProfileScreen
import com.lyadsky.noteapp.common.navigation.ScreenRoute
import com.lyadsky.noteapp.features.mainNavigation.viewModel.MainNavigationViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.qualifier.named

@Composable
fun MainNavigationScreen(
    navController: NavHostController = rememberNavController(),
    viewModelWrapper: ViewModelWrapper<MainNavigationViewModel> =
        getViewModel(qualifier = named("MainNavigationViewModel")),
) {
    val startDestination = ScreenRoute.Main.toString()
    val backStackState = navController.currentBackStackEntryAsState()
    val currentRoute = backStackState.value
        ?.destination?.route?.substringBefore("/") ?: startDestination

    DisposableEffect(key1 = viewModelWrapper) {
        viewModelWrapper.viewModel.onViewShown()
        onDispose { viewModelWrapper.viewModel.onViewHidden() }
    }
    Scaffold(
        bottomBar = {
            val isMainScreen = bottomNavigationItems.any { it.route.name == currentRoute }
            if (isMainScreen)
                BottomBar(ScreenRoute.valueOf(currentRoute)) { route: ScreenRoute ->
                    viewModelWrapper.viewModel.onBottomBarButtonClicked(route)
                }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = ScreenRoute.Main.name) {
                MainScreen()
            }
            composable(route = ScreenRoute.Profile.name) {
                ProfileScreen()
            }
        }
    }
}
