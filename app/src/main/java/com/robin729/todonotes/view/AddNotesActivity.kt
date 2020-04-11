package com.robin729.todonotes.view

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.util.Util
import com.robin729.todonotes.BuildConfig
import com.robin729.todonotes.R
import com.robin729.todonotes.utils.AppConstants
import kotlinx.android.synthetic.main.activity_add_notes.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddNotesActivity : AppCompatActivity() {


    private val REQUEST_CODE_GALLERY = 2
    val REQUEST_CODE_CAMERA = 1
    val PERMISSION_CODE = 1234
    var picturePath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        imageNotes.setOnClickListener {
            if (checkAndRequestPermission()) {
                setupDialog()
            }
        }

        submitBtn.setOnClickListener {
            if(titleEdt.text.toString().isNotBlank() && descriptionEdt.text.toString().isNotBlank()){
                val intent = Intent()
                intent.putExtra(AppConstants.TITLE, titleEdt.text.toString())
                intent.putExtra(AppConstants.DESCRIPTION, descriptionEdt.text.toString())
                intent.putExtra(AppConstants.IMAGE_PATH, picturePath)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Text fields cannot be empty", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun checkAndRequestPermission(): Boolean {
        val cameraPermission =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        val storagePermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val permissionNeeded = ArrayList<String>()
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            permissionNeeded.add(android.Manifest.permission.CAMERA)
        }
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            permissionNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (permissionNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionNeeded.toTypedArray(),
                PERMISSION_CODE
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupDialog()
                }
            }
        }
    }

    private fun setupDialog() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_selector, null)
        val txtViewCamera = view.findViewById<TextView>(R.id.txtViewCamera)
        val txtViewGallery = view.findViewById<TextView>(R.id.txtViewGallery)
        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(true)
            .create()

        txtViewCamera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val photoFiles: File? = createImage()
            if (photoFiles != null) {
                val photoURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".provider", photoFiles)
                picturePath = photoFiles.absolutePath
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, REQUEST_CODE_CAMERA)
                dialog.hide()
            }
        }

        txtViewGallery.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(intent, REQUEST_CODE_GALLERY)
            dialog.hide()
        }

        dialog.show()

    }

    private fun createImage(): File? {
        val timeStamp = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val fileName = "JPEG_${timeStamp}_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDir)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_GALLERY -> {
                    val selectedImage = data?.data
                    val filePath = arrayOf(MediaStore.Images.Media.DATA)
                    val c = contentResolver.query(selectedImage!!, filePath, null, null, null)
                    c?.moveToFirst()
                    val columnIndex = c?.getColumnIndex(filePath[0])
                    picturePath = c?.getString(columnIndex!!)!!
                    c.close()
                    Glide.with(this).load(picturePath).into(imageNotes)
                }

                REQUEST_CODE_CAMERA -> {
                    Glide.with(this).load(picturePath).into(imageNotes)
                }
            }
        }

    }
}
