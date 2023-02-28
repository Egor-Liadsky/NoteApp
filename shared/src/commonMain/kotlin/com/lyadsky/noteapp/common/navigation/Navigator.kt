package com.lyadsky.noteapp.common.navigation

enum class ScreenRoute {
    Main,
    Profile
}

interface Navigator {
    fun navigateBack()
    fun navigateToMain()
    fun navigateToProfile()
}
