package org.bhushan_kmp.notetakingkmp.di

import org.bhushan_kmp.notetakingkmp.data.MongoDB
import org.bhushan_kmp.notetakingkmp.vm.HomeViewModel
import org.bhushan_kmp.notetakingkmp.vm.NoteViewModel
import org.koin.dsl.module

val commonModule = module {
    single { MongoDB() }
    factory { HomeViewModel(get()) }
    factory { NoteViewModel(get()) }
}