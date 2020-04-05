package com.robin729.todonotes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by lazy{
        getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        isLoggedIn()
    }

    private fun isLoggedIn() {
        val loginStatus = sharedPreferences.getBoolean(AppConstants.LOGIN_STATUS, false)
        if(loginStatus){
            val intent = Intent(this, MyToDoNotesActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        finish()

    }
}
