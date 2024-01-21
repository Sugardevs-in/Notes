package com.example.notes.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class NoteState(
    val notes: List<Note> = emptyList(),
    val title: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf("")

)