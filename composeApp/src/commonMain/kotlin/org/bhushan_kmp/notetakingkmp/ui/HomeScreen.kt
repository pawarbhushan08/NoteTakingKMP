package org.bhushan_kmp.notetakingkmp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.bhushan_kmp.notetakingkmp.domain.Note
import org.bhushan_kmp.notetakingkmp.domain.RequestState
import org.bhushan_kmp.notetakingkmp.ui.components.ErrorScreen
import org.bhushan_kmp.notetakingkmp.ui.components.LoadingScreen
import org.bhushan_kmp.notetakingkmp.ui.components.NoteItemView
import org.bhushan_kmp.notetakingkmp.vm.HomeViewModel

class HomeScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<HomeViewModel>()
        val notes by viewModel.notes

        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("NoteApp") },//TODO: Add to string resources
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navigator.push(NoteScreen()) },
                    shape = RoundedCornerShape(size = 14.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Note"
                    )
                }
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp)
                ) {
                    ShowNotes(
                        modifier = Modifier.weight(1f),
                        notes = notes,
                        onSelectNote = {
                            navigator.push(NoteScreen(it))
                        },
                        onDeleteNote = {
                            viewModel.deleteNote(it)
                        }
                    )
                }
            }
        )
    }

    @Composable
    fun ShowNotes(
        modifier: Modifier = Modifier,
        notes: RequestState<List<Note>>,
        onSelectNote: ((Note) -> Unit)? = null,
        onDeleteNote: ((Note) -> Unit)? = null,
    ) {
        Column(modifier = modifier.fillMaxWidth()) {
            notes.DisplayResult(
                onLoading = { LoadingScreen() },
                onError = { ErrorScreen(message = it) },
                onSuccess = {
                    if (it.isNotEmpty()) {
                        LazyColumn(modifier = Modifier.padding(horizontal = 24.dp, vertical = 80.dp)) {
                            items(
                                items = it,
                                key = { note -> note.id.toHexString() }
                            ) { note ->
                                NoteItemView(
                                    note = note,
                                    onSelect = { onSelectNote?.invoke(note) },
                                    onDelete = { onDeleteNote?.invoke(note) }
                                )
                            }
                        }
                    } else {
                        ErrorScreen()
                    }
                }
            )
        }
    }
}