package com.coding.notesapplication.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "note_table")
@Parcelize
data class Note(@PrimaryKey(autoGenerate = true) var id: Long?, var title: String, var description: String, var priority: Int) : Parcelable {

    @Ignore
    constructor(title: String, description: String, priority: Int) : this(-1, title, description, priority) {
    }
}