package com.example.mynotes.roomDataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Word_Table")
data class Note(@ColumnInfo(name = "Text") val text: String, @PrimaryKey(autoGenerate = true) var id:Int=0)