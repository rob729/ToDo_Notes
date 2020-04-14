package com.robin729.todonotes.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.robin729.todonotes.R
import com.robin729.todonotes.adapter.BlogAdapter
import com.robin729.todonotes.model.Response
import kotlinx.android.synthetic.main.activity_blog.*


class BlogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        getBlogs()
    }

    private fun getBlogs() {
        AndroidNetworking.get("http://www.mocky.io/v2/5926ce9d11000096006ccb30")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsObject(Response::class.java, object : ParsedRequestListener<Response> {
                override fun onResponse(response: Response?) {
                    if (response != null) {
                        setupRecyclerView(response)
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.e("TAG", anError?.localizedMessage)
                }

            })
    }

    private fun setupRecyclerView(response: Response) {
        val blogAdapter = BlogAdapter(response.data)
        blogsRv.adapter = blogAdapter
    }
}
