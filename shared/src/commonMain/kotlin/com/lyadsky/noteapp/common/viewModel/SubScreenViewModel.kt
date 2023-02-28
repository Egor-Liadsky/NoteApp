package com.lyadsky.noteapp.common.viewModel

import com.lyadsky.noteapp.common.navigation.Navigator

interface SubScreenViewModel {
    val navigator: Navigator

    fun onBackButtonClick() {
        navigator.navigateBack()
    }
}
