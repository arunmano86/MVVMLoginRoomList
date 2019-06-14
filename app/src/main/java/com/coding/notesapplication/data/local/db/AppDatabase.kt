package com.coding.notesapplication.data.local.db


import android.content.Context
import android.os.AsyncTask
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.coding.notesapplication.data.local.dao.LoginDao
import com.coding.notesapplication.data.local.dao.NoteDao
import com.coding.notesapplication.models.Login
import com.coding.notesapplication.models.Note

@Database(entities = [Login::class, Note::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile private var instance : AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(LOCK) {
            instance?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase( context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "notes_database").addCallback(roomCallback).build()

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(@NonNull db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDBAsyncTask(instance).execute()
            }
        }

        private class PopulateDBAsyncTask(db: AppDatabase?) : AsyncTask<Void, Void, Void>() {
            private val noteDao: NoteDao
            private val loginDao: LoginDao
            init {
                noteDao = db!!.noteDao()
                loginDao = db.loginDao()
            }

            override fun doInBackground(vararg voids: Void): Void? {
                loginDao.insertLogin(Login("user@gmail.com", "123456"))
                loginDao.insertLogin(Login("arun@gmail.com", "123456"))

                noteDao.insertNote(Note("Title 1", "Description 1", 1))
                noteDao.insertNote(Note("Title 2", "Description 2", 2))
                noteDao.insertNote(Note("Title 3", "Description 3", 3))
                return null
            }
        }
    }
}