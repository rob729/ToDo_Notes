package com.robin729.todonotes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils

class MyToDoNotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_to_do_notes)
        var userName = intent.getStringExtra(AppConstants.USER_NAME)
        if(userName.isNullOrBlank()){
            val pref = getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)
            userName = pref.getString(AppConstants.FULL_NAME, "")
        }
        supportActionBar?.title = userName
    }
}
