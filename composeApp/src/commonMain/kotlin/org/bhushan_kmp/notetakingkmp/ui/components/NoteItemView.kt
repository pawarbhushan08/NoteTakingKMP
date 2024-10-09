package org.bhushan_kmp.notetakingkmp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.bhushan_kmp.notetakingkmp.domain.Note

@Composable
fun NoteItemView(
    note: Note,
    onSelect: (Note) -> Unit,
    onDelete: (Note) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(note)
            }
            .padding(top = 12.dp),
    ) {
        Row {
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = note.title,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                )
                Text(
                    text = note.content,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onDelete(note) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }

        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
    }
}