package org.bhushan_kmp.notetakingkmp.domain

sealed class NoteAction {
    data class Add(val note: Note) : NoteAction()
    data class Update(val note: Note) : NoteAction()
}