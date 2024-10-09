package org.bhushan_kmp.notetakingkmp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.bhushan_kmp.notetakingkmp.domain.Note
import org.bhushan_kmp.notetakingkmp.domain.NoteAction
import org.bhushan_kmp.notetakingkmp.vm.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
class NoteScreen(val note: Note? = null) : Screen {
    @Composable
    override fun Content() {
        val noteViewModel = getScreenModel<NoteViewModel>()

        var noteTitle by remember { mutableStateOf(note?.title ?: "") }
        var noteContent by remember { mutableStateOf(note?.content ?: "") }

        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Add/Edit Notes") },//TODO: Add to string resources
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Back Button"
                            )
                        }
                    }
                )
            },
            content = {
                Column(modifier = Modifier.padding(vertical = 80.dp, horizontal = 16.dp)) {
                    TextField(
                        value = noteTitle,
                        onValueChange = { noteTitle = it },
                        label = { Text("Title") }//TODO: Add to string resources
                    )
                    TextField(
                        modifier = Modifier.padding(top = 16.dp),
                        value = noteContent,
                        onValueChange = { noteContent = it },
                        label = { Text("Content") }//TODO: Add to string resources
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        enabled = noteContent.isNotBlank() && noteTitle.isNotBlank(),
                        onClick = {
                            if (note != null) {
                                noteViewModel.setAction(
                                    action = NoteAction.Update(
                                        Note().apply {
                                            id = note.id
                                            title = noteTitle
                                            content = noteContent
                                        }
                                    )
                                )
                            } else {
                                noteViewModel.setAction(
                                    action = NoteAction.Add(
                                        Note().apply {
                                            title = noteTitle
                                            content = noteContent
                                        }
                                    )
                                )                            }
                            navigator.pop()
                        },
                        content = { Text("Save") })//TODO: Add to string resources
                }
            }
        )
    }
}