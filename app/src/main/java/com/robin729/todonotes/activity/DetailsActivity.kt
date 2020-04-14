package com.robin729.todonotes.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.robin729.todonotes.R
import com.robin729.todonotes.utils.AppConstants
import kotlinx.android.synthetic.main.row_layout.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val title = intent.getStringExtra(AppConstants.TITLE)
        val description = intent.getStringExtra(AppConstants.DESCRIPTION)

        titleTxt.text = title
        descriptionTxt.text = description
    }
}
