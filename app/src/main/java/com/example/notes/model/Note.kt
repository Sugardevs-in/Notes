package com.example.notes.model

import java.util.UUID

data class Note(
    val id :UUID = UUID.randomUUID(),
    val title:String = "Android development",
    val description: String = "Description"
){}
