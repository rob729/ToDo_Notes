package com.robin729.todonotes.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.robin729.todonotes.R
import com.robin729.todonotes.onboarding.OnBoardingActivity
import com.robin729.todonotes.utils.AppConstants
import com.robin729.todonotes.utils.StoreSession

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getFCMToken()
        isLoggedIn()
    }

    private fun getFCMToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
                // Get new Instance ID token
                val token = task.result?.token

                //val msg = getString(R.string.msg_token_fmt, token)
                //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })
    }

    private fun isLoggedIn() {

        val loginStatus = StoreSession.read(AppConstants.LOGIN_STATUS)
        val onBoardedStatus = StoreSession.read(AppConstants.ON_BOARDED_SUCCESSFULLY)
        if (loginStatus) {
            val intent = Intent(this, MyToDoNotesActivity::class.java)
            startActivity(intent)
        } else {
            if (onBoardedStatus) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
        finish()

    }
}
