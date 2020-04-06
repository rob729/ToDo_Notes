package com.robin729.todonotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robin729.todonotes.R
import com.robin729.todonotes.model.Notes

class NotesAdapter(private val notesList: ArrayList<Notes>) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notesList[position]
        holder.titleTxt.text = item.title
        holder.descpTxt.text = item.description
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTxt = itemView.findViewById<TextView>(R.id.titleTxt)
        val descpTxt = itemView.findViewById<TextView>(R.id.descriptionTxt)
    }



}