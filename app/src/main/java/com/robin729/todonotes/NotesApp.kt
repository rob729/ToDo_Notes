package com.robin729.todonotes

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.robin729.todonotes.db.NotesDatabase
import com.robin729.todonotes.utils.StoreSession

class NotesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        StoreSession.init(this)
        AndroidNetworking.initialize(applicationContext)
    }

    fun getNotesDB(): NotesDatabase{
        return NotesDatabase.getInstance(this)
    }
}