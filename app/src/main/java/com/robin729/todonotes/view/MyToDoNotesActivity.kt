package com.robin729.todonotes.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton
import com.robin729.todonotes.NotesApp
import com.robin729.todonotes.utils.AppConstants
import com.robin729.todonotes.R
import com.robin729.todonotes.adapter.NotesAdapter
import com.robin729.todonotes.clicklistener.ItemClickListener
import com.robin729.todonotes.db.Notes
import kotlinx.android.synthetic.main.activity_my_to_do_notes.*

class MyToDoNotesActivity : AppCompatActivity() {

    private val notesList = ArrayList<Notes>()
    val ADD_NOTES_CODE = 100
    private val notesApp by lazy{
        applicationContext as NotesApp
    }
    val notesDao by lazy{
        notesApp.getNotesDB().notesDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_to_do_notes)

        val userName = if(intent.hasExtra(AppConstants.USER_NAME)){
            intent.getStringExtra(AppConstants.USER_NAME)
        } else {
            val pref = getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)
            pref.getString(AppConstants.FULL_NAME, "")
        }
        supportActionBar?.title = userName

        setupAdapter()
        getDatafromDb()

        fab.setOnClickListener {
            //setupDialog()
            val intent = Intent(this, AddNotesActivity::class.java)
            startActivityForResult(intent, ADD_NOTES_CODE)
        }
    }

    private fun getDatafromDb() {
        notesList.addAll(notesApp.getNotesDB().notesDao().getAllNotes())
    }

    private fun setupDialog(){
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_view_layout, null)
        val titleEdt = view.findViewById<EditText>(R.id.titleEdt)
        val descEdt = view.findViewById<EditText>(R.id.descriptionEdt)
        val submit = view.findViewById<MaterialButton>(R.id.submitBtn)

        val dialog = AlertDialog.Builder(this).setView(view).setCancelable(false).create()
        dialog.show()

        submit.setOnClickListener {
            if(titleEdt.text.toString().isNotBlank() && descEdt.text.toString().isNotBlank()){
                val note = Notes(title = titleEdt.text.toString(), desp = descEdt.text.toString(), imagePath = "")
                addNotestoDb(note)
                notesList.add(note)
            } else {
                Toast.makeText(this, "Text fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
    }

    private fun addNotestoDb(note: Notes) {
        notesDao.insert(note)
    }

    private fun setupAdapter(){
        val itemClickListener = object: ItemClickListener{
            override fun onClick(notes: Notes) {
                val intent = Intent(applicationContext, DetailsActivity::class.java)
                intent.putExtra(AppConstants.TITLE, notes.title)
                intent.putExtra(AppConstants.DESCRIPTION, notes.desp)
                startActivity(intent)
            }

            override fun onUpdate(notes: Notes) {
                notesDao.updateNotes(notes)
            }

        }
        val notesAdapter = NotesAdapter(notesList, itemClickListener)
        recycler_view.adapter = notesAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_NOTES_CODE){
            val title = data?.getStringExtra(AppConstants.TITLE)
            val descp = data?.getStringExtra(AppConstants.DESCRIPTION)
            val imagePath = data?.getStringExtra(AppConstants.IMAGE_PATH)

            val note = Notes(title = title!!, desp = descp!!, imagePath = imagePath!!)
            addNotestoDb(note)
            notesList.add(note)
            recycler_view.adapter?.notifyDataSetChanged()
        }
    }
}
