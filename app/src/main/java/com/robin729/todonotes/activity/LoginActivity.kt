package com.robin729.todonotes.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.robin729.todonotes.R
import com.robin729.todonotes.utils.AppConstants
import com.robin729.todonotes.utils.StoreSession
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            if (!name.text.toString().isBlank()) {
                val intent = Intent(this, MyToDoNotesActivity::class.java)
                intent.putExtra(AppConstants.FULL_NAME, name.text.toString())
                StoreSession.write(AppConstants.LOGIN_STATUS, true)
                StoreSession.write(AppConstants.FULL_NAME, name.text.toString())
                startActivity(intent)
                finish()
            } else {
                Snackbar.make(login_button, "Text field cannot be empty", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}
