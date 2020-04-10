package com.robin729.todonotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.checkbox.MaterialCheckBox
import com.robin729.todonotes.R
import com.robin729.todonotes.clicklistener.ItemClickListener
import com.robin729.todonotes.db.Notes

class NotesAdapter(private val notesList: ArrayList<Notes>, private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notesList[position]
        holder.titleTxt.text = item.title
        holder.descpTxt.text = item.desp
        holder.completeStatus.isChecked = item.isCompleted
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(item)
        }

        Glide.with(holder.itemView).load(item.imagePath).into(holder.imgNotes)

        holder.completeStatus.setOnCheckedChangeListener { compoundButton, b ->
            item.isCompleted = b
            itemClickListener.onUpdate(item)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTxt = itemView.findViewById<TextView>(R.id.titleTxt)
        val descpTxt = itemView.findViewById<TextView>(R.id.descriptionTxt)
        val completeStatus = itemView.findViewById<MaterialCheckBox>(R.id.completeStatus)
        val imgNotes = itemView.findViewById<ImageView>(R.id.imgNotes)
    }



}