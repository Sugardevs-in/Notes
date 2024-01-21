package com.example.notes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(

    val title:String,
    val description: String ,
    val dataAdded: Long,
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0
){}
