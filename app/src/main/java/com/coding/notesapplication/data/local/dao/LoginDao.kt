package com.coding.notesapplication.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coding.notesapplication.models.Login
import com.coding.notesapplication.models.Note
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLogin(login: Login)

    @Query("SELECT * FROM login_table WHERE emailAddress Like :emailAddress AND password LIKE :password")
    fun isLogin(emailAddress: String, password: String) : Single<Login>
}