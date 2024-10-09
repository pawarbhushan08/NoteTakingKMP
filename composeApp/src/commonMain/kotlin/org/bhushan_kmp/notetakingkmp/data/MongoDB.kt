package org.bhushan_kmp.notetakingkmp.data

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.bhushan_kmp.notetakingkmp.domain.Note
import org.bhushan_kmp.notetakingkmp.domain.RequestState

class MongoDB {
    private var realm: Realm? = null

    init {
        configureRealm()
    }

    private fun configureRealm() {
        if (realm == null || realm?.isClosed() == true) {
            val config = RealmConfiguration.Builder(
                schema = setOf(Note::class)
            ).build()
            realm = Realm.open(config)
        }
    }

    fun getAllNotes(): Flow<RequestState<List<Note>>> {
        return realm?.query(Note::class)
            ?.asFlow()
            ?.map { notes ->
                RequestState.Success(
                    data = notes.list
                )
            } ?: flow { RequestState.Error(message = "Notes are not available.") }
    }

    suspend fun addNote(note: Note) {
        realm?.write { copyToRealm(note) }
    }

    suspend fun updateNote(updatedNote: Note): RequestState<Note> {
        var result: RequestState<Note> = RequestState.Error(message = "Unable to update note")
        realm?.write {
            try {
                val noteToUpdate = query<Note>(query = "id == $0", updatedNote.id).first().find()
                    ?: return@write RequestState.Error(message = "Note not found")

                findLatest(noteToUpdate)?.let { note ->
                    note.title = updatedNote.title
                    note.content = updatedNote.content
                }
                result = RequestState.Success(data = noteToUpdate)
            } catch (e: Exception) {
                result = RequestState.Error(message = e.message ?: "Unable to update note")
            }
        }
        return result

    }

    suspend fun deleteNote(note: Note) {
        realm?.write {
            try {
                val queriedNote = query<Note>(query = "id == $0", note.id)
                    .first()
                    .find()
                queriedNote?.let {
                    findLatest(it)?.let { currentTask ->
                        delete(currentTask)
                    }
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}
