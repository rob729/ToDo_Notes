package com.robin729.todonotes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by lazy{
        getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)
    }

    val editor: SharedPreferences.Editor by lazy {
        sharedPreferences.edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            if (!(name.text.toString().isBlank() && user_name.text.toString().isBlank())) {
                val intent = Intent(this, MyToDoNotesActivity::class.java)
                intent.putExtra(AppConstants.FULL_NAME, name.text.toString())
                editor.putBoolean(AppConstants.LOGIN_STATUS, true)
                editor.putString(AppConstants.FULL_NAME, name.text.toString())
                editor.apply()
                startActivity(intent)
                finish()
            } else {
                Snackbar.make(login_button, "Text field cannot be empty", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}
