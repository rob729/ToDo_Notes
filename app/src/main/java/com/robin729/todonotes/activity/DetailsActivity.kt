package com.robin729.todonotes.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.robin729.todonotes.R
import com.robin729.todonotes.utils.AppConstants
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.row_layout.*
import kotlinx.android.synthetic.main.row_layout.descriptionTxt
import kotlinx.android.synthetic.main.row_layout.titleTxt

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val title = intent.getStringExtra(AppConstants.TITLE)
        val description = intent.getStringExtra(AppConstants.DESCRIPTION)
        val imagePath = intent.getStringExtra(AppConstants.IMAGE_PATH)

        titleTxt.text = title
        descriptionTxt.text = description
        if(!imagePath.isNullOrBlank()){
            Glide.with(this).load(imagePath).into(imageNotes)
        }
    }
}
