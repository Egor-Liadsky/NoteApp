package com.lyadsky.noteapp.android.di

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyadsky.noteapp.common.viewModel.KmpViewModel
import com.lyadsky.noteapp.common.viewModel.StatefulKmpViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ViewModelWrapper<T : KmpViewModel>(
    val viewModel: T
) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        viewModel.onCleared()
    }
}

class StatefulViewModelWrapper<T : StatefulKmpViewModel<S>, S>(
    val viewModel: T
) : ViewModel() {
    val state = mutableStateOf(viewModel.state.value)

    init {
        viewModelScope.launch {
            viewModel.state.collectLatest {
                state.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModel.onCleared()
    }
}


