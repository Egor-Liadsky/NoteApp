package com.lyadsky.noteapp.android.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.lyadsky.noteapp.common.navigation.Navigator
import com.lyadsky.noteapp.common.navigation.ScreenRoute

interface AndroidNavigator : Navigator {
    var navController: NavHostController?
}

class NavigatorImpl(
    override var navController: NavHostController? = null
) : AndroidNavigator {
    override fun navigateBack() {
        navController?.popBackStack()
    }

    override fun navigateToMain() {
        navigateToNavBarDestination(ScreenRoute.Main)
    }

    override fun navigateToProfile() {
        navigateToNavBarDestination(ScreenRoute.Profile)
    }

    private fun navigateToNavBarDestination(root: ScreenRoute) {
        navController?.navigate(root.name) {
            popUpTo(navController!!.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
