package com.lyadsky.noteapp.features.mainNavigation.viewModel

import com.lyadsky.noteapp.common.navigation.Navigator
import com.lyadsky.noteapp.common.navigation.ScreenRoute
import com.lyadsky.noteapp.common.viewModel.KmpViewModel
import com.lyadsky.noteapp.common.viewModel.KmpViewModelImpl
import org.koin.core.component.KoinComponent

interface MainNavigationViewModel : KmpViewModel {
    fun onBottomBarButtonClicked(route: ScreenRoute)
}

class MainNavigationViewModelImpl(
    private val navigator: Navigator
) : KoinComponent, KmpViewModelImpl(), MainNavigationViewModel {
    override fun onBottomBarButtonClicked(route: ScreenRoute) {
        when (route) {
            ScreenRoute.Main -> navigator.navigateToMain()
            ScreenRoute.Profile -> navigator.navigateToProfile()
            else -> {}
        }
    }
}
