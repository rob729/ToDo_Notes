package com.robin729.todonotes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.robin729.todonotes.adapter.NotesAdapter
import com.robin729.todonotes.model.Notes
import kotlinx.android.synthetic.main.activity_my_to_do_notes.*

class MyToDoNotesActivity : AppCompatActivity() {

    private val notesList = ArrayList<Notes>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_to_do_notes)
        var userName = intent.getStringExtra(AppConstants.USER_NAME)
        if(userName.isNullOrBlank()){
            val pref = getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)
            userName = pref.getString(AppConstants.FULL_NAME, "")
        }
        supportActionBar?.title = userName

        setupAdapter()

        fab.setOnClickListener {
            setupDialog()
        }
    }

    private fun setupDialog(){
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_view_layout, null)
        val titleEdt = view.findViewById<EditText>(R.id.titleEdt)
        val descEdt = view.findViewById<EditText>(R.id.descriptionEdt)
        val submit = view.findViewById<MaterialButton>(R.id.submitBtn)

        val dialog = AlertDialog.Builder(this).setView(view).setCancelable(false).create()
        dialog.show()

        submit.setOnClickListener {
            notesList.add(Notes(titleEdt.text.toString(), descEdt.text.toString()))
            dialog.dismiss()
        }
    }

    private fun setupAdapter(){
        val notesAdapter = NotesAdapter(notesList)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = notesAdapter
    }
}
