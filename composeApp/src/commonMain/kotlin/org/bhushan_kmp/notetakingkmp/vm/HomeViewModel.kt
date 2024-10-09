package org.bhushan_kmp.notetakingkmp.vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.bhushan_kmp.notetakingkmp.data.MongoDB
import org.bhushan_kmp.notetakingkmp.domain.Note
import org.bhushan_kmp.notetakingkmp.domain.RequestState

class HomeViewModel(private val mongoDB: MongoDB) : ScreenModel {

    private var _notes: MutableState<RequestState<List<Note>>> = mutableStateOf(RequestState.Idle)
    val notes: MutableState<RequestState<List<Note>>> = _notes

    init {
        _notes.value = RequestState.Loading
        screenModelScope.launch {
            delay(500)
            mongoDB.getAllNotes().collectLatest {
                notes.value = it
            }
        }
    }

    fun deleteNote(note: Note) {
        screenModelScope.launch {
            mongoDB.deleteNote(note)
        }
    }
}