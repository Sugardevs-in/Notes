package com.example.notes

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.notes.ui.theme.NotesTheme
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            NotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val savedNotes = readNotesFromFile(this, "notes.txt")
                    StickyNotesApp(savedNotes)
                }
            }
        }
    }
}

// Function to write notes to a file
fun writeNotesToFile(context: Context, notes: List<Note>, fileName: String) {
    try {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
            val writer = BufferedWriter(OutputStreamWriter(outputStream))

            for (note in notes) {
                writer.write(note.content)
                writer.newLine()
                writer.write("##~~~##@@##%") // Add delimiter after each note
                writer.newLine()
                Log.d("AppDebug", "Writing note: ${note.content}")
            }

            writer.close()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

// Function to read notes from a file
fun readNotesFromFile(context: Context, fileName: String): List<Note> {
    val notes = mutableListOf<Note>()
    try {
        val fileInputStream = context.openFileInput(fileName)
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)


        val delimiter = "##~~~##@@##%" // Choose a delimiter that's unlikely to appear in notes

        var currentLine: String? = bufferedReader.readLine()
        var noteContent = ""

        while (currentLine != null) {
            if (currentLine == delimiter) {
                notes.add(Note(noteContent))
                noteContent = ""
            } else {
                noteContent += currentLine + "\n"
            }
            currentLine = bufferedReader.readLine()
        }

        bufferedReader.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return notes
}

// Data class to represent a Note
data class Note(var content: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Note) return false
        return content == other.content
    }

    override fun hashCode(): Int {
        return content.hashCode()
    }
}

// Function to remove a note from the file
fun removeNoteFromFile(context: Context, note: Note, fileName: String) {
    try {
        val notes = readNotesFromFile(context, fileName)
        val newNotes = notes.filterNot { it.content == note.content }
        writeNotesToFile(context, newNotes, fileName)
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e("AppDebug", "Error removing note: ${e.message}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalLayoutApi

@Composable
fun StickyNotesApp(savedNotes: List<Note>) {
    val context = LocalContext.current
    val notes = remember { mutableStateListOf(*savedNotes.toTypedArray()) }
    var isVisible by rememberSaveable { mutableStateOf(false) }
    var text by rememberSaveable { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        if (isVisible) {
            // Add edit mode content here
        } else {
            LazyColumn {
                items(notes) { note ->
                    // Display the NotesCard for each note
                    NotesCard(
                        notes = note,
                        onDelete = {
                            removeNoteFromFile(context, it, "notes.txt")
                            notes.remove(it)
                        },
                        onUpdate = {
                            // Add the update logic here to save the updated note list
                            updateAndSaveNote(context, notes, it, "notes.txt")
                        }
                    )
                }
            }
        }

        if (isVisible) {
            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .padding(32.dp)
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Notes") },
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp, 40.dp)
                )
            }
        }
        Row (
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxSize()

        )   {
            Row(

                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)
                    .padding(20.dp, 80.dp, 40.dp, 55.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                if (isVisible) {
                    val isTextEmpty = text.isBlank()
                    IconButton(
                        onClick = {
                            if (!isTextEmpty) {
                                // Only add a note if the text is not empty
                                notes.add(Note(text))
                                text = ""
                                writeNotesToFile(context, notes, "notes.txt")
                            }
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(24))
                            .size(64.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add"
                        )
                    }
                }
                Spacer(modifier = Modifier.width(24.dp))
                IconButton(
                    onClick = { isVisible = !isVisible },
                    modifier = Modifier
                        .clip(RoundedCornerShape(24))
                        .size(64.dp)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    if (isVisible) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit",
                        )
                    }
                }
            }
        }
    }
}

fun updateAndSaveNote(context: Context, notes: List<Note>, editedNote: Note, fileName: String) {
    val updatedNotes = notes.map { if (it == editedNote) editedNote else it }
    writeNotesToFile(context, updatedNotes, fileName)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesCard(notes: Note, onDelete: (Note) -> Unit,  onUpdate: (Note) -> Unit) {
    var isEditing by remember { mutableStateOf(false) }
    var editText by remember { mutableStateOf(notes.content) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        if (isEditing) {
            // Edit mode
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = editText,
                    onValueChange = {
                        editText = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            // Save the edited text and exit edit mode
                            notes.content = editText
                            isEditing = false
                            onUpdate(notes)
                            // You might want to save the updated note list here
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Check"
                        )
                    }
                }
            }
        } else {
            // View mode
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = notes.content,
                    modifier = Modifier.weight(1f) // Occupy remaining horizontal space
                )
                Row {
                    IconButton(
                        onClick = {
                            onDelete(notes)
                        },
                        modifier = Modifier
                            .size(34.dp)
                            .padding(0.dp, 0.dp, 0.dp, 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete"
                        )
                    }
                    IconButton(
                        onClick = {
                            isEditing = true
                            editText = notes.content
                        },
                        modifier = Modifier
                            .size(34.dp)
                            .padding(8.dp, 0.dp, 0.dp, 2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit"
                        )
                    }
                }
            }
        }
    }
}




@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun Show() {
    NotesTheme {
        StickyNotesApp(emptyList())
    }
}