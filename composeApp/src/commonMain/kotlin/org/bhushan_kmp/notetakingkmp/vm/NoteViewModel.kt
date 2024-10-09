package org.bhushan_kmp.notetakingkmp.vm

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.bhushan_kmp.notetakingkmp.data.MongoDB
import org.bhushan_kmp.notetakingkmp.domain.Note
import org.bhushan_kmp.notetakingkmp.domain.NoteAction

class NoteViewModel(private val mongoDB: MongoDB) : ScreenModel {

    fun setAction(action: NoteAction) {
        when (action) {
            is NoteAction.Add -> addNote(action.note)
            is NoteAction.Update -> updateNote(action.note)
        }
    }

    private fun addNote(note: Note) {
        screenModelScope.launch {
            mongoDB.addNote(note)
        }
    }

    private fun updateNote(note: Note) {
        screenModelScope.launch {
            mongoDB.updateNote(note)
        }
    }
}