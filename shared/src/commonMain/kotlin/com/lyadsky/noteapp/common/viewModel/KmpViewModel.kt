package com.lyadsky.noteapp.common.viewModel

import com.squareup.sqldelight.db.Closeable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

interface KmpViewModel {
    val scope: CoroutineScope
    val jobs: MutableList<Job>

    fun onViewShown()
    fun onViewHidden()
    fun onCleared()
}

abstract class KmpViewModelImpl : KmpViewModel {
    override val scope = CoroutineScope(Dispatchers.Main)
    override val jobs: MutableList<Job> = mutableListOf()

    override fun onViewShown() {

    }

    suspend fun exceptionHandleable(
        executionBlock: suspend () -> Unit,
        failureBlock: (suspend (exception: Throwable) -> Unit)? = null,
        completionBlock: (suspend () -> Unit)? = null
    ) {
        try {
            executionBlock()
        } catch (exception: Throwable) {
            //TODO add error handling
            println("Throwable caught, cause: ${exception.cause}, message: ${exception.message}")
            if (exception !is CancellationException)
                failureBlock?.invoke(exception)
        } finally {
            completionBlock?.invoke()
        }
    }

    override fun onViewHidden() {
        clearJobs()
    }

    override fun onCleared() {
        clearJobs()
    }

    private fun clearJobs() {
        jobs.forEach {
            it.cancel()
        }
        jobs.clear()
    }
}

interface KmpState<T> {
    val state: StateFlow<T>
}

interface StatefulKmpViewModel<T> : KmpState<T>, KmpViewModel {
}

abstract class StatefulKmpViewModelImpl<T> : StatefulKmpViewModel<T>, KmpViewModelImpl() {

    //Changing state for iOS
    fun onChange(provideNewState: ((T) -> Unit)): Closeable {
        val job = Job()

        state.onEach {
            provideNewState(it)
        }.launchIn(
            CoroutineScope(Dispatchers.Main + job)
        )

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }

    }
}
