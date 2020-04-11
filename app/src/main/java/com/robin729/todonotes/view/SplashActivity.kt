package com.robin729.todonotes.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.robin729.todonotes.utils.AppConstants
import com.robin729.todonotes.R

class SplashActivity : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by lazy{
        getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        isLoggedIn()
        getFCMToken()
    }

    private fun getFCMToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                //val msg = getString(R.string.msg_token_fmt, token)
                //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })
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
