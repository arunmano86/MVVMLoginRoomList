package com.coding.notesapplication.models

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize
import android.util.Patterns
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "login_table")
@Parcelize
data class Login(@PrimaryKey(autoGenerate = true) var id: Long?, var emailAddress: String, var password: String) : Parcelable {
    @Ignore
    constructor( emailAddress: String,  password: String) : this(null, emailAddress, password) {
    }

    fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
    }


    fun isPasswordLengthGreaterThan5(): Boolean {
        return password.length > 5
    }
}