package com.lyadsky.noteapp.android.di

import com.lyadsky.noteapp.android.navigation.AndroidNavigator
import com.lyadsky.noteapp.android.navigation.NavigatorImpl
import com.lyadsky.noteapp.common.navigation.Navigator
import com.lyadsky.noteapp.features.mainNavigation.viewModel.MainNavigationViewModelImpl
import com.lyadsky.noteapp.features.notes.state.NoteState
import com.lyadsky.noteapp.features.notes.viewModel.NoteViewModel
import com.lyadsky.noteapp.features.notes.viewModel.NoteViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun androidModule() = module {
    single<AndroidNavigator> { NavigatorImpl() }
    single<Navigator> { get<AndroidNavigator>() }

    viewModel(named("MainNavigationViewModel")) {
        ViewModelWrapper(MainNavigationViewModelImpl(get()))
    }

    viewModel(named("NoteViewModel")) {
        StatefulViewModelWrapper<NoteViewModel, NoteState>(
            NoteViewModelImpl(get())
        )
    }
}
