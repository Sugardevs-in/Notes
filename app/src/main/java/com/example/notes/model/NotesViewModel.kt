package com.example.notes.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    private val dao: NoteDao
):ViewModel() {
        private  val isSortedByDateAdded = MutableStateFlow(true)

        @OptIn(ExperimentalCoroutinesApi::class)
        private val notes =
            isSortedByDateAdded.flatMapLatest {sort ->
                if(sort){
                    dao.getNotesOrderedByData()
                }else {
                    dao.getNotesOrderedByTitle()
                }

            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),emptyList())

            val _state = MutableStateFlow(NoteState())
            val state = combine(_state, isSortedByDateAdded, notes){ state ,  isSortedByDateAdded, notes ->
                state.copy(
                    notes = notes
                )

            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),NoteState())

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.DeleteNote -> {
             viewModelScope.launch {
                 dao.deleteNote(event.note)
             }
            }
            is NotesEvent.SaveNote -> {
                val note = Note(

                        title = state.value.title.value,
                        description = state.value.description.value,
                        dataAdded = System.currentTimeMillis()

                )
                viewModelScope.launch {
                    dao.upsertNote(note)
                }

                _state.update {
                    it.copy(
                        title = mutableStateOf(""),
                        description = mutableStateOf("")
                    )
                }
            }
            NotesEvent.SortNotes -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value
            }
        }
    }
}