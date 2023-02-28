package com.lyadsky.noteapp.common.di

import com.lyadsky.noteapp.common.data.local.DBRepository
import com.lyadsky.noteapp.common.data.local.DBRepositoryImpl
import com.lyadsky.noteapp.features.notes.service.NoteService
import com.lyadsky.noteapp.features.notes.service.NoteServiceImpl
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(), platformModule())
    }


fun commonModule() = module {

    // Repository
    single<DBRepository> { DBRepositoryImpl() }

    // Service
    single<NoteService> { NoteServiceImpl() }
}
