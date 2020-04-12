package com.robin729.todonotes

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.robin729.todonotes.db.NotesDatabase

class NotesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext)
    }

    fun getNotesDB(): NotesDatabase{
        return NotesDatabase.getInstance(this)
    }
}