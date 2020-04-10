package com.robin729.todonotes

import android.app.Application
import com.robin729.todonotes.db.NotesDatabase

class NotesApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }

    fun getNotesDB(): NotesDatabase{
        return NotesDatabase.getInstance(this)
    }
}